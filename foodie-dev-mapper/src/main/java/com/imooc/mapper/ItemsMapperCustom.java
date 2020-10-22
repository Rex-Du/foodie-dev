package com.imooc.mapper;

import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.pojo.vo.SearchItemVO;
import com.imooc.pojo.vo.ShopcartItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom {
    public List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> map);

    public List<SearchItemVO> searchItemsByName(@Param("paramsMap") Map<String, Object> map);

    public List<SearchItemVO> searchItemsByCatId(@Param("paramsMap") Map<String, Object> map);

    public List<ShopcartItemsVO> queryItemsBySpecIds(@Param("itemSpecIds") List<String> itemSpecIds);

    public int decreaseItemSpecStock(@Param("specId") String specId, @Param("buyCounts") int buyCounts);
}