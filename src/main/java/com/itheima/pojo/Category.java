package com.itheima.pojo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author longteng
 * @date 2023/11/12 19:47
 **/
@Data
public class Category {
    private Integer id;
    private String categoryName;
    private String categoryAlias;
    private String createUser;
    private LocalDateTime creatTime;
    private LocalDateTime updateTime;

    public void setCreatTime(LocalDateTime creatTime) {
        this.creatTime = creatTime;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }


    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryAlias='" + categoryAlias + '\'' +
                ", createUser='" + createUser + '\'' +
                ", creatTime=" + creatTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
