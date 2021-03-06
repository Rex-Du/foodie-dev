package com.imooc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.enums.CommentLevel;
import com.imooc.enums.YesOrNo;
import com.imooc.mapper.*;
import com.imooc.pojo.*;
import com.imooc.pojo.vo.ItemCommentCountVO;
import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.pojo.vo.SearchItemVO;
import com.imooc.pojo.vo.ShopcartItemsVO;
import com.imooc.service.ItemsService;
import com.imooc.utils.DesensitizationUtil;
import com.imooc.utils.PagedGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class ItemsServiceImpl implements ItemsService {
    @Autowired
    private ItemsMapper itemsMapper;

    @Autowired
    private ItemsImgMapper itemsImgMapper;

    @Autowired
    private ItemsSpecMapper itemsSpecMapper;

    @Autowired
    private ItemsParamMapper itemsParamMapper;

    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;


    @Override
    public Items queryItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> queryItemImgsByItemId(String itemId) {
        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);

        return itemsImgMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemSpecsByItemId(String itemId) {
        Example example = new Example(ItemsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);

        return itemsSpecMapper.selectByExample(example);
    }

    @Override
    public ItemsSpec queryItemSpecBySpecId(String specId) {
        ItemsSpec itemsSpec = new ItemsSpec();
        itemsSpec.setId(specId);
        return itemsSpecMapper.selectOne(itemsSpec);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemParamByItemId(String itemId) {
        Example example = new Example(ItemsParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);

        return itemsParamMapper.selectOneByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemCommentCountVO queryItemCommentCount(String itemId) {
        Integer goodCount = queryItemCommentCount(itemId, CommentLevel.GOOD.type);
        Integer normalCount = queryItemCommentCount(itemId, CommentLevel.NORMAL.type);
        Integer badCount = queryItemCommentCount(itemId, CommentLevel.BAD.type);

        ItemCommentCountVO countVO = new ItemCommentCountVO();
        countVO.setTotalCounts(goodCount + normalCount + badCount);
        countVO.setGoodCounts(goodCount);
        countVO.setNormalCounts(normalCount);
        countVO.setBadCounts(badCount);
        return countVO;
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("itemId", itemId);
        map.put("level", level);
        // mybatis-pagehelper
        PageHelper.startPage(page, pageSize);
        List<ItemCommentVO> list = itemsMapperCustom.queryItemComments(map);
        for (ItemCommentVO vo : list) {
            vo.setNickname(DesensitizationUtil.commonDisplay(vo.getNickname()));
        }
        return setterPagedGrid(list, page);
    }

    @Override
    public PagedGridResult queryItemsByKeywords(String itemName, String sort, Integer page, Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("itemName", itemName);
        map.put("sort", sort);

        PageHelper.startPage(page, pageSize);
        List<SearchItemVO> searchItemVOS = itemsMapperCustom.searchItemsByName(map);
        return setterPagedGrid(searchItemVOS, page);
    }

    @Override
    public PagedGridResult queryItemsByCatId(String catId, String sort, Integer page, Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("catId", catId);
        map.put("sort", sort);

        PageHelper.startPage(page, pageSize);
        List<SearchItemVO> searchItemVOS = itemsMapperCustom.searchItemsByCatId(map);
        return setterPagedGrid(searchItemVOS, page);
    }

    @Override
    public List<ShopcartItemsVO> queryItemsBySpecIds(String specIds) {
        String[] ids = specIds.split(",");
        return itemsMapperCustom.queryItemsBySpecIds(Arrays.asList(ids));
    }

    @Override
    public String queryItemMainImgById(String itemId) {
        ItemsImg itemsImg = new ItemsImg();
        itemsImg.setItemId(itemId);
        itemsImg.setIsMain(YesOrNo.YES.type);

        ItemsImg img = itemsImgMapper.selectOne(itemsImg);
        return img.getUrl();
    }

    @Override
    public int decreaseItemSpecStock(String specId, int buyCounts) {
        int result = itemsMapperCustom.decreaseItemSpecStock(specId, buyCounts);
        if(result!=1)
            throw new RuntimeException("库存不足");

        return result;
    }

    private PagedGridResult setterPagedGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }

    Integer queryItemCommentCount(String itemId, int level) {
        ItemsComments comments = new ItemsComments();
        comments.setItemId(itemId);
        comments.setCommentLevel(level);
        return itemsCommentsMapper.selectCount(comments);
    }

}
