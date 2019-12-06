package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * CSS链接与角色关联实体类
 */
@TableName("t_ext_css_role")
public class ExtCssRole extends BaseEntity {

    private Long extCssId;

    private Long roleId;

    public Long getExtCssId() {
        return extCssId;
    }

    public void setExtCssId(Long extCssId) {
        this.extCssId = extCssId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
