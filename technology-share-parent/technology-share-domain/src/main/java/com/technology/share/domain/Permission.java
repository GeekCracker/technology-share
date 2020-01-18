package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    /**权限图标*/
    private String permissionIcon;

    /**权限序号*/
    private Byte permissionSort;

    /**权限启用状态*/
    private Boolean permissionStatus;

    /**权限类型*/
    private Byte permissionType;

    /**父级权限*/
    @TableField(exist = false)
    private String parentId;

    @TableField(value = "parent_id")
    @JsonIgnore
    private Long parentIdRaw;

    @TableField(exist = false)
    private List<Permission> children;

    /**是否删除(0:不删除1:删除)(添加时自动填充，且默认为不删除状态)*/
    @TableLogic(value = "0",delval = "1")
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;

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

    public String getPermissionIcon() {
        return permissionIcon;
    }

    public void setPermissionIcon(String permissionIcon) {
        this.permissionIcon = permissionIcon;
    }

    public Byte getPermissionSort() {
        return permissionSort;
    }

    public void setPermissionSort(Byte permissionSort) {
        this.permissionSort = permissionSort;
    }

    public Boolean getPermissionStatus() {
        return permissionStatus;
    }

    public void setPermissionStatus(Boolean permissionStatus) {
        this.permissionStatus = permissionStatus;
    }

    public Byte getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(Byte permissionType) {
        this.permissionType = permissionType;
    }

    public String getParentId() {
        if(parentIdRaw != null && parentIdRaw > 0){
            return IdTypeHandler.encode(parentIdRaw);
        }
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Long getParentIdRaw() {
        if(parentId !=null && !"".equals(parentId)){
            return IdTypeHandler.decode(parentId);
        }
        return parentIdRaw;
    }

    public void setParentIdRaw(Long parentIdRaw) {
        this.parentIdRaw = parentIdRaw;
    }

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }


    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
