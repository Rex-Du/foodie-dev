package com.imooc.pojo.vo;

import lombok.Data;

@Data
public class ItemCommentCountVO {
    private int totalCounts;
    private int goodCounts;
    private int normalCounts;
    private int badCounts;
}
