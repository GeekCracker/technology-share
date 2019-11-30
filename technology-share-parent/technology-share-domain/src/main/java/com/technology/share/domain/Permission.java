package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.technology.share.handler.IdTypeHandler;

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

    /**权限链接*/
    private String permissionLink;

    /**权限启用状态*/
    private Boolean permissionStatus;

    /**父级权限*/
    @TableField(exist = false)
    private String parentId;

    @TableField(value = "parent_id")
    private Long parentIdRow;

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

    public String getPermissionLink() {
        return permissionLink;
    }

    public void setPermissionLink(String permissionLink) {
        this.permissionLink = permissionLink;
    }

    public Boolean getPermissionStatus() {
        return permissionStatus;
    }

    public void setPermissionStatus(Boolean permissionStatus) {
        this.permissionStatus = permissionStatus;
    }

    public String getParentId() {
        if(parentIdRow != null && parentIdRow > 0){
            return IdTypeHandler.encode(parentIdRow);
        }
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Long getParentIdRow() {
        if(parentId !=null && !"".equals(parentId)){
            return IdTypeHandler.decode(parentId);
        }
        return parentIdRow;
    }

    public void setParentIdRow(Long parentIdRow) {
        this.parentIdRow = parentIdRow;
    }

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }
}
