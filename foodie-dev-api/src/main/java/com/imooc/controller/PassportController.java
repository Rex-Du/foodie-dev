package com.imooc.controller;

import com.imooc.service.UserService;
import com.imooc.utils.IMOOCJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassportController {
    @Autowired
    private UserService userService;

    @GetMapping("/isUsernameExist")
    public IMOOCJSONResult isUsernameExist(String name){
        if(StringUtils.isBlank(name)){
            return new IMOOCJSONResult(400, "用户名为空", null);
        }
        boolean exist = userService.isUsernameExist(name);
        if(exist){
            return IMOOCJSONResult.errorMsg("用户名已存在");
        }else {
            return IMOOCJSONResult.ok();
        }
    }
}
