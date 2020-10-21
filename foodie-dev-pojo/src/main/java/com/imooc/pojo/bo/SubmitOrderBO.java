package com.imooc.pojo.bo;

import lombok.Data;

@Data
public class SubmitOrderBO {
    private String addressId;
    private String itemSpecIds;
    private String leftMsg;
    private Integer payMethod;
    private String userId;

    @Override
    public String toString() {
        return "SubmitOrderBO{" +
                "addressId='" + addressId + '\'' +
                ", itemSpecIds='" + itemSpecIds + '\'' +
                ", leftMsg='" + leftMsg + '\'' +
                ", payMethod=" + payMethod +
                ", userId='" + userId + '\'' +
                '}';
    }
}
