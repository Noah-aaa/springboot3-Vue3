package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.*;

/**
 * @author longteng
 * @date 2023/11/12 20:29
 **/
@Mapper
public interface UserMapper {


    // 根据用户名查询用户
    @Select("select * from user where username = #{username}")
    User findByUserName(String username);
    // 添加
    @Insert("Insert into user(username,password,create_time,update_time)"+"values (#{username},#{password},now(),now())")
    void add(@Param("username") String username,@Param("password") String md5String);

    @Update("update user set nickname = #{nickname},email = #{email} ,update_time=#{updateTime} where id = #{id}")
    void update(User user);

    @Update("update user set user_pic = #{avatarUrl},update_time = now() where id = #{id}")
    void updateAvatar(String avatarUrl,Integer id);

    @Update("update  user set password = #{md5String},update_time = now() where id = #{id}")
    void updatePwd(String md5String, Integer id);
}
