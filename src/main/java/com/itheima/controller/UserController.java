package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author longteng
 * @date 2023/11/12 19:44
 **/
@RestController // @Responsebody + @Controller
@RequestMapping("user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    // 用户
    @PostMapping("register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password){ // 注解验证，乐意简化代码逻辑
        // 异常需要进行处理那么的话，就需要全局异常处理器
        // 查询用户，是否重复
        User user = userService.findByUserName(username);
        if(user == null){
            // 没有被占用
            userService.register(username,password);
            return Result.success();
            // 注册成功
        }else{
            // 占用
            return Result.error("用户名已存在");
        }
    }

    @PostMapping("login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$")String username,
                        @Pattern(regexp = "^\\S{5,16}$")String password){
        User user  = userService.findByUserName(username);
        if(user != null){
            if(user.getUsername().equals(username)
                    &&user.getPassword().equals(Md5Util.getMD5String(password))){
                // 返回token令牌.用于身份验证
                // json web token // http://jwt.io
                // 第一部分：header(头)，记录令牌类型、签名算法、例如：{"alg":"HS256","type":JWT}
                return Result.success(null);
            }else{
                return Result.error("密码错误");
            }
        }
        return Result.error("用户名不存在");
    }
}
