package com.itheima.service;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;

/**
 * @author longteng
 * @date 2023/11/16 14:11
 **/
public interface ArticleService {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
