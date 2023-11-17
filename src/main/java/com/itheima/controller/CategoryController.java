package com.itheima.controller;

import com.itheima.pojo.Category;
import com.itheima.pojo.Result;
import com.itheima.service.CategoryService;
import com.itheima.utils.ThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author longteng
 * @date 2023/11/16 11:18
 **/
@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     *  增加文章
    **/
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> list(){
        List<Category> list = categoryService.list();
        return Result.success(list);
    }
    /**
     *  获取文章具体详细信息
     *
    **/
    @GetMapping("detail")
    public Result<Category> detail(Integer id){
        Category detail = categoryService.detail(id);
        return Result.success(detail);
    }

    /**
    * validation 的分组校验，指定类的接口
    * */
    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success();
    }

}
