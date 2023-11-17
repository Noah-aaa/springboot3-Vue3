package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author longteng
 * @date 2023/11/16 18:15
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    private Long total; // 总条数
    private List<T> data; // 当前页数集合
}
