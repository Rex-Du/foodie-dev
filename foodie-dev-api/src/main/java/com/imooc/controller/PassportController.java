package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "注册和登陆", tags = {"注册和登陆"})
@RestController
@RequestMapping("passport")
public class PassportController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "user register", httpMethod = "GET")
    @GetMapping("usernameIsExist")
    public IMOOCJSONResult isUsernameExist(String username) {
        if (StringUtils.isBlank(username)) {
            return new IMOOCJSONResult(400, "用户名为空", null);
        }
        boolean exist = userService.isUsernameExist(username);
        if (exist) {
            return IMOOCJSONResult.errorMsg("用户名已存在");
        } else {
            return IMOOCJSONResult.ok();
        }
    }

    @ApiOperation(value = "用户注册", httpMethod = "POST")
    @PostMapping("regist")
    public IMOOCJSONResult regist(@RequestBody UserBO userBO,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
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

        Users user = userService.createUser(userBO);
        user = setNullProperty(user);
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(user), true);
        return IMOOCJSONResult.ok(user);

    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("login")
    public IMOOCJSONResult login(@RequestBody UserBO userBO,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password))
            return new IMOOCJSONResult(400, "username or password is null", null);

        boolean exist = userService.isUsernameExist(username);
        if (!exist)
            return IMOOCJSONResult.errorMsg("用户不存在");
        Users user = userService.selectUser(userBO);
        if (user == null)
            return IMOOCJSONResult.errorMsg("username or password error");
        user = setNullProperty(user);
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(user), true);
        return IMOOCJSONResult.ok(user);
    }

    @ApiOperation(value = "退出登录", notes = "退出登录", httpMethod = "POST")
    @PostMapping("logout")
    public IMOOCJSONResult logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, "user");
        // todo 退出登录需要清空购物车
        // todo 分布式会话中需要清除用户数据
      return IMOOCJSONResult.ok();
    }

    private Users setNullProperty(Users user) {
        user.setPassword(null);
        user.setMobile(null);
        user.setCreatedTime(null);
        user.setBirthday(null);
        return user;
    }
}
