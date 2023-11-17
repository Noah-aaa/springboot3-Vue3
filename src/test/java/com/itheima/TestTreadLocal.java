package com.itheima;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author longteng
 * @date 2023/11/14 21:58
 **/
@SpringBootTest
public class TestTreadLocal {

    @Test
    public void testTreadLocalandSetandGet(){
        // 获取treadLocal，线程隔离，提供线程局部变量
        ThreadLocal t1 = new ThreadLocal();

        // 开启两个线程，其中()->{} lambda表达式
        new Thread(()->{
            t1.set("消炎");
            System.out.println(Thread.currentThread().getName()+t1.get());
            System.out.println(Thread.currentThread().getName()+t1.get());
            System.out.println(Thread.currentThread().getName()+t1.get());
        },"蓝色").start();
        new Thread(()->{
            t1.set("唐三");
            System.out.println(Thread.currentThread().getName()+t1.get());
            System.out.println(Thread.currentThread().getName()+t1.get());
            System.out.println(Thread.currentThread().getName()+t1.get());
        },"黑色").start();

    }
}
