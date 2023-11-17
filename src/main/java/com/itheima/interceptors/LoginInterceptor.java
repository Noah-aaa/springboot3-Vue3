package com.itheima.interceptors;

import com.itheima.utils.JwtUtil;
import com.itheima.utils.ThreadUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @author longteng
 * @date 2023/11/14 21:11
 * 先设置拦截器，交给ioc容器进行统一管理
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {
    // 重写prehandler方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 令牌验证
        String token = request.getHeader("Authorization");
        try{
            // 解析token令牌是否有效
            Map<String, Object> map = JwtUtil.parseToken(token);
            // 把业务数据存储到Thread中
            ThreadUtils.set(map);
            // 开始放行
            return true;
        }catch(Exception e){
            // 设置响应状态码
            response.setStatus(401);
            // 不放行
            return false;
        }
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 完成之后，清除token 防止内存泄露
        ThreadUtils.remove();
    }
}
