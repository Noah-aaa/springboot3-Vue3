package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mapper.ArticleMapper;
import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.service.ArticleService;
import com.itheima.utils.ThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author longteng
 * @date 2023/11/16 14:11
 **/
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadUtils.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        Map<String,Object> map = ThreadUtils.get();
        Integer userId = (Integer) map.get("id");
        // 创建pageBean对象
        PageBean<Article> pb = new PageBean<>();
        // 开启分页查询 PageHelper
        PageHelper.startPage(pageNum,pageSize);
        // 调用mapper
        List<Article> as =  articleMapper.list(userId,categoryId,state);
        // Page 提供了方法，可以获取PageHelper分页查询后，得到总记录条数，和当前页数据
        PageInfo<Article> p = new PageInfo<>(as);
        // 把数据填充到PageBean对象里
        pb.setTotal(p.getTotal());
        pb.setData(p.getList());
        return pb;
    }
}
