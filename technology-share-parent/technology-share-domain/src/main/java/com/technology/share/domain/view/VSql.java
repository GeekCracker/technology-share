package com.technology.share.domain.view;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.technology.share.domain.BaseEntity;
import com.technology.share.handler.IdTypeHandler;
import lombok.Data;

/**
 * @description: sql语句视图po
 * @author: 朱俊亮
 * @time: 2020/4/18 12:56
 */
@TableName(value = "v_sql")
@Data
public class VSql extends BaseEntity {

    /**名称*/
    private String name;

    /**SQL语句所属数据库ID*/
    @TableField(exist = false)
    private String databaseId;

    /**SQL语句所属数据库ID*/
    @TableField("database_id")
    @JsonIgnore
    private Long databaseIdRaw;

    /**SQL语句所属数据库名称*/
    private String databaseName;

    /**SQL语句*/
    private String sqlStatement;

    /**描述信息*/
    private String description;

    /**角色ID列表*/
    @TableField(exist = false)
    private String roleIds;

    @TableField("role_ids")
    @JsonIgnore
    private String roleIdsRaw;

    /**SQL语句类型(1：增加、2：删除、3：修改、4：查询、5、DDL数据库定义语言)*/
    private Integer type;

    /**角色名称列表*/
    private String roleNames;

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

    public String getRoleIds() {
        if(roleIdsRaw != null && !"".equals(roleIdsRaw)){
            String [] roleArr = roleIdsRaw.split(",");
            for(int i = 0;i< roleArr.length;i++){
                roleArr[i] = IdTypeHandler.encode(Long.parseLong(roleArr[i]));
            }
            return JSONObject.toJSONString(roleArr);
        }
        return roleIds;
    }

    public String getRoleIdsRaw() {
        if(roleIds != null && !"".equals(roleIds)){
            String [] roleArr = roleIds.split(",");
            for(int i = 0;i< roleArr.length;i++){
                roleArr[i] = String.valueOf(IdTypeHandler.decode(roleArr[i]));
            }
            return JSONObject.toJSONString(roleArr);
        }
        return roleIdsRaw;
    }
}
