package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 角色实体类
 */
@TableName("t_role")
public class Role extends BaseEntity {

    /**角色名称*/
    private String roleName;

    /**角色状态*/
    private Boolean roleStatus;

    /**权限ID列表*/
    @TableField(exist = false)
    private String pIds;

    /**是否删除(0:不删除1:删除)(添加时自动填充，且默认为不删除状态)*/
    @TableLogic(value = "0",delval = "1")
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(Boolean roleStatus) {
        this.roleStatus = roleStatus;
    }

    public String getpIds() {
        return pIds;
    }

    public void setpIds(String pIds) {
        this.pIds = pIds;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
