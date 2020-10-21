package com.imooc.service;

import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;

import java.util.List;

public interface AddressService {
    public List<UserAddress> queryAll(String userId);

    public void addAddress(AddressBO addressBO);

    public void setDefaultAddress(String userId, String addressId);

    public void updateAddress(AddressBO addressBO);

    public void deleteAddress(String userId, String addressId);
}
