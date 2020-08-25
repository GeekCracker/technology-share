package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description: SQL语句
 * @author: 朱俊亮
 * @time: 2020/4/7 18:36
 */
@TableName("t_sql")
@Data
public class Sql extends BaseEntity {

    /**名称*/
    private String name;

    /**SQL语句*/
    private String sqlStatement;

    /**描述信息*/
    private String description;

    /**角色ID列表*/
    @TableField(exist = false)
    private String roleIds;

    /**是否启用*/
    private Boolean enable;
}
