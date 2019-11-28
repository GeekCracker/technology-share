package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 角色实体类
 */
@TableName("t_role")
public class Role extends BaseEntity {


    /**角色名称*/
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
