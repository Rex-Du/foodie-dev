package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;

public interface UserService {
    public boolean isUsernameExist(String name);

    public Users createUser(UserBO userBO);
}
