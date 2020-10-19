package com.imooc.mapper;

import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.pojo.vo.SearchItemVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom {
    public List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> map);

    public List<SearchItemVO> searchItemsByName(@Param("paramsMap") Map<String, Object> map);

    public List<SearchItemVO> searchItemsByCatId(@Param("paramsMap") Map<String, Object> map);
}