package com.itheima.mapper;

import com.itheima.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * @author longteng
 * @date 2023/11/16 11:27
 **/
@Mapper
public interface CategoryMapper {
    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time)"
            +"values (#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);

    @Select("select * from category where create_user = #{id}")
    List<Category> list(Integer id);
    @Select("select * from category where id = #{id}")
    Category detail(Integer id);

    @Update("update category set category_name = #{categoryName},category_alias = #{categoryAlias},update_time = #{updateTime}" +
            "where id = #{id}")
    void update(Category category);
}
