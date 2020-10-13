package com.imooc.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * 最新推荐商品VO
 */
@Data
public class NewItemsVO {
//        <id column="rootCatId" property="rootCatId"/>
//        <result column="rootCatName" property="rootCatName"/>
//        <result column="slogan" property="slogan"/>
//        <result column="catImage" property="catImage"/>
//        <result column="bgColor" property="bgColor"/>
    private Integer rootCatId;
    private String rootCatName;
    private String slogan;
    private String catImage;
    private String bgColor;

    private List<SimpleItemVO> simpleItemList;
}
