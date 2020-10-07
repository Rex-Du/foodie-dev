package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.DateUtil;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "register and login", tags = {"for register and login"})
@RestController
@RequestMapping("passport")
public class PassportController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "user register", httpMethod = "GET")
    @GetMapping("isUsernameExist")
    public IMOOCJSONResult isUsernameExist(String name) {
        if (StringUtils.isBlank(name)) {
            return new IMOOCJSONResult(400, "用户名为空", null);
        }
        boolean exist = userService.isUsernameExist(name);
        if (exist) {
            return IMOOCJSONResult.errorMsg("用户名已存在");
        } else {
            return IMOOCJSONResult.ok();
        }
    }

    @ApiOperation(value = "user register", httpMethod = "POST")
    @PostMapping("regist")
    public IMOOCJSONResult regist(UserBO userBO) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword))
            return new IMOOCJSONResult(400, "username or password is null", null);

        boolean exist = userService.isUsernameExist(username);
        if (exist)
            return IMOOCJSONResult.errorMsg("用户名已存在");

        if (!password.equals(confirmPassword))
            return IMOOCJSONResult.errorMsg("password is not same");

        userService.createUser(userBO);

        return IMOOCJSONResult.ok();

    }
}
