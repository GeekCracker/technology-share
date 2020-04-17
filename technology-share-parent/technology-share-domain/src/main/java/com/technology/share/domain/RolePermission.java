package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色权限关联表
 */
@TableName("t_role_permission")
@Data
public class RolePermission extends BaseEntity {

    /**角色ID*/
    private Long roleId;

    /**权限ID*/
    private Long permissionId;
}
