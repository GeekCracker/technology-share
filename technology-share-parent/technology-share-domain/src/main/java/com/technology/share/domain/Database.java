package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description: 数据库实体类
 * @author: 朱俊亮
 * @time: 2020/4/7 16:09
 */
@TableName("t_database")
@Data
public class Database extends BaseEntity{

    /**数据库名称*/
    private String name;

    /**数据库链接*/
    private String url;

    /**数据库登录用户名*/
    private String username;

    /**数据库登录密码*/
    private String password;

    /**数据库描述信息*/
    private String description;

    /**是否启用*/
    private Boolean enable;
}
