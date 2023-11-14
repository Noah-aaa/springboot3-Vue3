package com.itheima.service.impl;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author longteng
 * @date 2023/11/12 20:18
 **/
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User user = userMapper.findByUserName(username);
        return user;
    }

    @Override
    public void register(String username, String password) {
        // 密码还要进行加密处理，如果以明文的处理，那么肯定会出事情
        /**
         * 一般用MD5
        **/
        String md5String = Md5Util.getMD5String(password);
        userMapper.add(username,md5String);
    }
}
