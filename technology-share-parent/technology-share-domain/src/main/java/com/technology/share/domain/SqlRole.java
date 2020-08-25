package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description: SQL语句拥有角色
 * @author: 朱俊亮
 * @time: 2020/4/18 13:04
 */
@Data
@TableName(value = "t_sql_role")
public class SqlRole extends BaseEntity {

    /**SQL语句ID*/
    private Long sqlId;

    /**角色ID*/
    private Long roleId;
}
