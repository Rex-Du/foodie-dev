package com.imooc.controller;

import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;
import com.imooc.service.AddressService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "用户地址", tags = "用户地址的相关接口")
@RequestMapping("address")
@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Transactional(propagation = Propagation.SUPPORTS)
    @PostMapping("list")
    public IMOOCJSONResult list(@ApiParam(name = "userId", value = "用户ID", required = true)
                                @RequestParam String userId) {
        if (StringUtils.isBlank(userId))
            return IMOOCJSONResult.errorMsg("");
        List<UserAddress> userAddresses = addressService.queryAll(userId);
        return IMOOCJSONResult.ok(userAddresses);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @PostMapping("add")
    public IMOOCJSONResult add(@RequestBody AddressBO addressBO) {
        addressService.addAddress(addressBO);
        return IMOOCJSONResult.ok();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @PostMapping("setDefalut")
    public IMOOCJSONResult setDefalut(@ApiParam(name = "userId", value = "用户ID", required = true)
                                      @RequestParam String userId,
                                      @ApiParam(name = "addressId", value = "地址ID", required = true)
                                      @RequestParam String addressId) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId))
            return IMOOCJSONResult.ok();

        addressService.setDefaultAddress(userId, addressId);
        return IMOOCJSONResult.ok();
    }

    // update
    @Transactional(propagation = Propagation.REQUIRED)
    @PostMapping("update")
    public IMOOCJSONResult update(@RequestBody AddressBO addressBO) {
        addressService.updateAddress(addressBO);
        return IMOOCJSONResult.ok();
    }
    // delete
    @Transactional(propagation = Propagation.REQUIRED)
    @PostMapping("delete")
    public IMOOCJSONResult delete(@ApiParam(name = "userId", value = "用户ID", required = true)
                                      @RequestParam String userId,
                                      @ApiParam(name = "addressId", value = "地址ID", required = true)
                                      @RequestParam String addressId) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId))
            return IMOOCJSONResult.ok();

        addressService.deleteAddress(userId, addressId);
        return IMOOCJSONResult.ok();
    }
}
