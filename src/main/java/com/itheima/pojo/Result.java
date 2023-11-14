package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Target;

/**
 * @author longteng
 * @date 2023/11/12 20:07
 * 响应统一结果
 **/

@NoArgsConstructor  // lombok的无参构造器
@AllArgsConstructor  // lombok的有参数构造器
@Data
public class Result<T> {
    private Integer code; // 业务状态码 0-成功 1-失败
    private String message; // 提示信息
    private T data; // 泛型是需要传递的

    // 响应成功并且可以携带数据返回
    public static <E> Result<E> success(E data){
        return new Result<>(0,"操作成功", data);
    }

    // 重写响应成功，且不带参数方法
    public static <E> Result<E> success(){
        return new Result<>(0,"操作成功",null);
    }

    // 失败返回
    public static Result error(String message){
        return new Result(1,message,null);
    }

}
