package com.technology.share.domain.view;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.technology.share.domain.BaseEntity;
import com.technology.share.domain.Permission;
import lombok.Data;

import java.util.List;

@TableName(value = "v_role",resultMap = "vRoleResultMap")
@Data
public class VRole extends BaseEntity {

    /**角色名称*/
    private String roleName;

    /**权限树*/
    @TableField(exist = false)
    private List<Permission> permissionTree;
}
