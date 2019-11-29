package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * 权限实体类
 */
@TableName("t_permission")
public class Permission extends BaseEntity {

    /**权限名*/
    private String permissionName;

    /**权限位*/
    private String permissionBit;

    /**权限下标*/
    private String permissionIndex;

    /**权限链接*/
    private String permissionLink;

    /**父级权限*/
    private Long parentId;

    @TableField(exist = false)
    private List<Permission> children;

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

    public String getPermissionIndex() {
        return permissionIndex;
    }

    public void setPermissionIndex(String permissionIndex) {
        this.permissionIndex = permissionIndex;
    }

    public String getPermissionLink() {
        return permissionLink;
    }

    public void setPermissionLink(String permissionLink) {
        this.permissionLink = permissionLink;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }
}
