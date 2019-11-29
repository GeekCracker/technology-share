package com.technology.share.domain.view;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.technology.share.domain.BaseEntity;
import com.technology.share.domain.Permission;

import java.util.List;

@TableName(value = "v_t_role",resultMap = "vRoleResultMap")
public class VRole extends BaseEntity {

    /**角色名称*/
    private String roleName;

    /**权限树*/
    @TableField(exist = false)
    private List<Permission> permissionTree;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Permission> getPermissionTree() {
        return permissionTree;
    }

    public void setPermissionTree(List<Permission> permissionTree) {
        this.permissionTree = permissionTree;
    }
}
