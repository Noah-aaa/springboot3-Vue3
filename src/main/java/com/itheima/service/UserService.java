package com.itheima.service;

import com.itheima.pojo.User;

/**
 * @author longteng
 * @date 2023/11/12 20:17
 **/
public interface UserService {
    User findByUserName(String username);

    void register(String username, String password);
}
