package com.imooc.controller;

import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.service.OrderService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "用户订单", tags = "用户订单的相关接口")
@RequestMapping("orders")
@RestController
public class OrdersController {
    @Autowired
    private OrderService orderService;

    @Transactional(propagation = Propagation.REQUIRED)
    @PostMapping("create")
    public IMOOCJSONResult create(@RequestBody SubmitOrderBO submitOrderBO) {
        // 1.创建订单
        String orderId = orderService.createOrder(submitOrderBO);
        // 2.创建订单后从购物车中移除已结算的商品
        // todo 整合redis之后，完善购物车中已结算的商品清除
        // 3.向支付中心发送当前订单，用于保存支付中心的订单数据
        return IMOOCJSONResult.ok(orderId);
    }
}
