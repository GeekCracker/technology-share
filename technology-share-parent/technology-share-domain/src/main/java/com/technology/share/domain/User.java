package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.technology.share.domain.view.VRole;

@TableName("t_user")
public class User extends BaseEntity {

    /**用户名*/
    private String userName;

    /**用户密码*/
    private String userPassword;

    /**年龄*/
    private Long age;

    /**角色ID*/
    private Long roleId;

    /**角色*/
    @TableField(exist = false)
    private VRole role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public VRole getRole() {
        return role;
    }

    public void setRole(VRole role) {
        this.role = role;
    }
}
