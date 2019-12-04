package com.technology.share.domain.view;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.technology.share.domain.BaseEntity;
import com.technology.share.handler.IdTypeHandler;

@TableName(value = "v_user")
public class VUser extends BaseEntity {

    /**用户名*/
    private String userName;

    /**用户密码*/
    private String userPassword;

    /**年龄*/
    private Long age;

    /**用户头像*/
    private String userHead;

    /**联系方式*/
    private String userPhone;

    /**角色ID*/
    @TableField(exist = false)
    private String roleId;

    /**角色ID*/
    @JsonIgnore
    @TableField(value = "role_id")
    private Long roleIdRaw;

    private String roleName;

    /**用户启用状态*/
    private Boolean userStatus;

    /**是否删除(0:不删除1:删除)(添加时自动填充，且默认为不删除状态)*/
    @TableLogic(value = "0",delval = "1")
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;

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

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getRoleId() {
        if(roleIdRaw != null && roleIdRaw > 0) {
            return IdTypeHandler.encode(roleIdRaw);
        }
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Long getRoleIdRaw() {
        if(roleId != null && !"".equals(roleId)){
            return IdTypeHandler.decode(roleId);
        }
        return roleIdRaw;
    }

    public void setRoleIdRaw(Long roleIdRaw) {
        this.roleIdRaw = roleIdRaw;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
