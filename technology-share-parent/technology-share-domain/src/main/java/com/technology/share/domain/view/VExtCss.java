package com.technology.share.domain.view;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.technology.share.domain.BaseEntity;
import com.technology.share.handler.IdTypeHandler;

import java.util.Arrays;

/**
 * CSS链接视图
 */
@TableName("v_ext_css")
public class VExtCss extends BaseEntity {

    /**外部CSS链接*/
    private String url;

    /**描述信息*/
    private String description;

    /**角色ID列表*/
    @TableField(exist = false)
    private String roleIds;

    /**角色ID列表*/
    @TableField("role_ids")
    @JsonIgnore
    private String roleIdsRaw;

    /**角色ID名称列表*/
    private String roleName;

    /**是否启用（0:禁用1:启用）*/
    private Boolean enable;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoleIds() {
        if(roleIdsRaw != null && !"".equals(roleIdsRaw)){
            String [] roleArr = roleIdsRaw.split(",");
            for(int i = 0;i< roleArr.length;i++){
                roleArr[i] = IdTypeHandler.encode(Long.parseLong(roleArr[i]));
            }
            return JSONObject.toJSONString(roleArr);
        }
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getRoleIdsRaw() {
        if(roleIds != null && !"".equals(roleIds)){
            String [] roleArr = roleIds.split(",");
            for(int i = 0;i< roleArr.length;i++){
                roleArr[i] = String.valueOf(IdTypeHandler.decode(roleArr[i]));
            }
            return JSONObject.toJSONString(roleArr);
        }
        return roleIdsRaw;
    }

    public void setRoleIdsRaw(String roleIdsRaw) {
        this.roleIdsRaw = roleIdsRaw;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
