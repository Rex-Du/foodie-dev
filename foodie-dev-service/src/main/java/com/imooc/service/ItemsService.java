package com.imooc.service;

import com.imooc.pojo.*;
import com.imooc.pojo.vo.ItemCommentCountVO;
import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.utils.PagedGridResult;

import java.util.List;

public interface ItemsService {
    public Items queryItemById(String itemId);

    public List<ItemsImg> queryItemImgsByItemId(String itemId);

    public List<ItemsSpec> queryItemSpecsByItemId(String itemId);

    public ItemsParam queryItemParamByItemId(String itemId);

    public ItemCommentCountVO queryItemCommentCount(String itemId);

    public PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);

    public PagedGridResult queryItemsByKeywords(String itemName, String sort, Integer page, Integer pageSize);

    public PagedGridResult queryItemsByCatId(String catId, String sort, Integer page, Integer pageSize);

}
