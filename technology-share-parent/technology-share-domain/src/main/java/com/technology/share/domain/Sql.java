package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.technology.share.handler.IdTypeHandler;
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

    /**SQL语句所属数据库ID*/
    @TableField(exist = false)
    private String databaseId;

    /**SQL语句所属数据库ID*/
    @JsonIgnore
    @TableField("database_id")
    private Long databaseIdRaw;

    /**角色ID列表*/
    @TableField(exist = false)
    private String roleIds;

    /**SQL语句类型(1：增加、2：删除、3：修改、4：查询、5、DDL数据库定义语言、6：设置用户权限)*/
    private Integer type;

    /**是否启用*/
    private Boolean enable;

    public String getDatabaseId() {
        if(databaseIdRaw != null){
            return IdTypeHandler.encode(databaseIdRaw);
        }
        return databaseId;
    }

    public Long getDatabaseIdRaw() {
        if(databaseId != null){
            return IdTypeHandler.decode(databaseId);
        }
        return databaseIdRaw;
    }
}
