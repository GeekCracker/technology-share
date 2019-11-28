package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 权限实体类
 */
@TableName("t_permission")
public class Permission extends BaseEntity {

    /**权限名*/
    private String permissionName;

    /**权限位*/
    private String permissionBit;

    /**父级权限*/
    private Long parentId;

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionBit() {
        return permissionBit;
    }

    public void setPermissionBit(String permissionBit) {
        this.permissionBit = permissionBit;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
