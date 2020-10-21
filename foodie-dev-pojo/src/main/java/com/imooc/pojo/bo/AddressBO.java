package com.imooc.pojo.bo;

import lombok.Data;

@Data
public class AddressBO {
    private String addressId;
    private String userId;
    private String city;
    private String detail;
    private String district;
    private String mobile;
    private String province;
    private String receiver;
}
