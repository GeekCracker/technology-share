package com.technology.share.domain.view;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.technology.share.domain.BaseEntity;
import com.technology.share.domain.Table;
import lombok.Data;

import java.util.List;

/**
 * @description: 数据库信息视图
 * @author: 朱俊亮
 * @time: 2020/4/7 16:40
 */
@TableName("v_database")
@Data
public class VDatabase extends BaseEntity {

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

    @TableField(exist = false)
    private List<Table> tables;
}