package com.itheima.service;

import com.itheima.pojo.Category;

import java.util.List;

/**
 * @author longteng
 * @date 2023/11/16 11:18
 **/
public interface CategoryService {
    void add(Category category);

    List<Category> list();

    Category detail(Integer id);

    void update(Category category);
}
