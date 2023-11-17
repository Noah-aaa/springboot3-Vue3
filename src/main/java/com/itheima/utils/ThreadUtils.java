package com.itheima.utils;

import org.apache.logging.log4j.spi.ObjectThreadContextMap;

/**
 * @author longteng
 * @date 2023/11/14 22:24
 * 线程隔离提供变量
 * 多个用户即多个线程，存储隔离提供变量
 **/

public class ThreadUtils {
    // 提供ThreadLocal对象
    private static final ThreadLocal tl = new ThreadLocal();


    // 根据键获取值
    public static <T> T get(){
        return (T) tl.get();
    }

    // 存储键值对
    public static void set(Object value){
        tl.set(value);
    }

    // 清除ThreadLocal 防止内除泄露
    public static void remove(){
        tl.remove();
    }
}
