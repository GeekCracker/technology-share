package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * JS链接与角色关联实体类
 */
@TableName("t_ext_js_role")
public class ExtJsRole extends BaseEntity {

    private Long extJsId;

    private Long roleId;

    public Long getExtJsId() {
        return extJsId;
    }

    public void setExtJsId(Long extJsId) {
        this.extJsId = extJsId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
