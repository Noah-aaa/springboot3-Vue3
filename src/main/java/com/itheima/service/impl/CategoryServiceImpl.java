package com.itheima.service.impl;

import com.itheima.mapper.CategoryMapper;
import com.itheima.pojo.Category;
import com.itheima.service.CategoryService;
import com.itheima.utils.ThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author longteng
 * @date 2023/11/16 11:19
 **/
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void add(Category category) {
        // category 只传递category_name 和 category_alias
        // 所以我们需要补充完整的属性值
        category.setCreatTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        // 获取创建用户id和username
        Map<String, Object> map = ThreadUtils.get();
        Integer userId = (Integer) map.get("id");
        category.setCreateUser(userId);
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        Map<String, Object> map = ThreadUtils.get();
        Integer id = (Integer) map.get("id");
        List<Category> list = categoryMapper.list(id);
        return list;
    }

    @Override
    public Category detail(Integer id) {
        Category category = categoryMapper.detail(id);
        return category;
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }
}
