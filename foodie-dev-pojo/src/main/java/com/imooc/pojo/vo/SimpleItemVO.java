package com.imooc.pojo.vo;

import lombok.Data;

@Data
public class SimpleItemVO {
//                <id column="itemId" property="itemId"/>
//            <result column="itemName" property="itemName"/>
//            <result column="itemUrl" property="itemUrl"/>
//            <result column="created_time" property="created_time"/>
    private Integer itemId;
    private String itemName;
    private String itemUrl;
}
