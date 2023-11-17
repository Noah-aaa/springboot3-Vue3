package com.itheima.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author longteng
 * @date 2023/11/12 19:47
 **/
@Data
public class Category {
    @NotNull(groups = Update.class) // 不能不传 groups 自定义分组。group是一个数组，可以指定多个分组
    private Integer id;
    @NotEmpty
    private String categoryName;
    @NotEmpty // 不能不传，且传过来的不能是字符串
    private String categoryAlias;
    private int createUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    // 自定义分组接口，继承默认分组如果属性上没有group属性，就是default分组
    public interface Update extends Default {

    }
    public interface Add extends  Default{

    }
    public void setCreatTime(LocalDateTime creatTime) {
        this.createTime = creatTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryAlias() {
        return categoryAlias;
    }

    public void setCategoryAlias(String categoryAlias) {
        this.categoryAlias = categoryAlias;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }


    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryAlias='" + categoryAlias + '\'' +
                ", createUser='" + createUser + '\'' +
                ", creatTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
