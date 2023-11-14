package com.itheima;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author longteng
 * @date 2023/11/13 22:13
 **/
@SpringBootTest
public class JwtTest {

    @Test
    public void testGen(){
        Map<String,Object> claims = new HashMap<>();
        // 添加信息
        claims.put("id",1);
        claims.put("username","张三");
        String token = JWT.create()
                .withClaim("user",claims) // 添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12)) // 添加过期时间
                .sign(Algorithm.HMAC256("itheima")); // 指定算法，配置密钥
        System.out.println(token);
    }

    @Test
    public void testParse(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE2OTk5Mjg1OTd9." +
                "7C978yoHG15edGJiXUPZ7qgO4UeaUl5ZzaEfA5bpxZQ";

        // 请求一个JWT算法
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("itheima")).build();
        DecodedJWT verify = jwtVerifier.verify(token);// 验证token，生成一个解析后的jwt对象
        Map<String, Claim> claims = verify.getClaims();
        System.out.println(claims.get("user"));
    }

}
