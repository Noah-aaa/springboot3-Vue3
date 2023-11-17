package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadUtils;
import jakarta.validation.constraints.Pattern;
import lombok.extern.log4j.Log4j2;
import org.apache.el.parser.Token;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author longteng
 * @date 2023/11/12 19:44
 **/
@RestController // @Responsebody + @Controller
@RequestMapping("user")
@Log4j2
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    // 用户
    /** PostMapping 在服务器新建一个资源
    * */
    @PostMapping("register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password){ // 注解验证，乐意简化代码逻辑
        // 异常需要进行处理那么的话，就需要全局异常处理器
        // 查询用户，是否重复
        User user = userService.findByUserName(username);
        log.info("user = " , username);
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
    public Result<String> login(@RequestParam("username") @Pattern(regexp = "^\\S{5,16}$") String username, @RequestParam("password")@Pattern(regexp = "^\\S{5,16}$") String password){
        log.info("username = {} ", username);
        User user  = userService.findByUserName(username);
        log.info("user = {}", user);
        if(user == null)
            return Result.error("用户不存在");

        if(user.getPassword().equals(Md5Util.getMD5String(password))){
                // 返回token令牌.用于身份验证
                // json web token // http://jwt.io
                // 第一部分：header(头)，记录令牌类型、签名算法、例如：{"alg":"HS256","type":JWT}
                Map<String,Object> claims = new HashMap<>();
                claims.put("id",user.getId());
                claims.put("username",user.getUsername());
                String token = JwtUtil.genToken(claims);
                return Result.success(token);
            }else{
                return Result.error("密码错误");
            }
    }

    /**
    * 查询所有的
     *   从服务器里面取出资源(一个或多个)
    **/
    @GetMapping("userInfo")
    public Result<User> userInfo(/*@RequestHeader("Authorization") String token*/) {
        // 根据用户名查询用户名,应当从请求头里面进行获取
/*        Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/
        Map<String, Object> clamis = ThreadUtils.get();
        User user = userService.findByUserName((String) clamis.get("username"));
        return Result.success(user);
    }

    /**
     * 更新用户信息
     * 在服务器里面更新资源
    **/
    @PutMapping("update")
    public Result upadate(@RequestBody @Validated User user){
        // 校验参数的同时还需要判断id是否与登录用户账号一致
        Map<String,Object> clamis = ThreadUtils.get();
        if(user.getId() != clamis.get("id") ){
            log.info("用户id + {}",clamis.get("id"));
            return Result.error("修改id不一致");
        }
        userService.update(user);
        return Result.success();
    }
    /**
     * pathchMapping 在服务器更新资源，patch更新各别属性
     *
    **/
    @PatchMapping("updateAvatar")
    public Result updateAvatar(@RequestParam("avatarUrl") @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }
    /**
    * 更新用户密码
    **/
    @PatchMapping("updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd)&&!StringUtils.hasLength(newPwd)&&!StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要参数");
        }
        if(!newPwd.equals(rePwd)){
            return Result.error("两次密码不一致");
        }
        // 获取用户名
        Map<String,Object> map = ThreadUtils.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        if(!user.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码不正确");
        }
        userService.updatePwd(oldPwd);
        return Result.success();
    }
}
