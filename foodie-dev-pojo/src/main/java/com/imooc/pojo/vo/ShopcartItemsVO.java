package com.imooc.pojo.vo;

import lombok.Data;

@Data
public class ShopcartItemsVO {
    private String itemId;
    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private int priceDiscount;
    private int priceNormal;
}
