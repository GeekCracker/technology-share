package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 角色权限关联表
 */
@TableName("t_role_permission")
public class RolePermission extends BaseEntity {

    /**角色ID*/
    private Long roleId;

    /**权限ID*/
    private Long permissionId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
