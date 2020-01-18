package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.technology.share.handler.IdTypeHandler;

import java.util.List;

@TableName("t_menu")
public class Menu extends BaseEntity{
    /**菜单名称*/
    private String name;
    /**图标*/
    private String icon;
    /**跳转链接*/
    private String url;
    /**父级目录ID*/
    @TableField(exist = false)
    private String parentId;
    /**父级目录ID*/
    @TableField(value = "parent_id")
    @JsonIgnore
    private Long parentIdRaw;
    /**子级菜单*/
    @TableField(exist = false)
    private List<Menu> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
