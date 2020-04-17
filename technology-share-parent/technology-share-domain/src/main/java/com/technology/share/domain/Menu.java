package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.technology.share.handler.IdTypeHandler;
import lombok.Data;

import java.util.List;

@TableName("t_menu")
@Data
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
    /**排序字段*/
    private Long sort;
    /**子级菜单*/
    @TableField(exist = false)
    private List<Menu> children;

    public String getParentId() {
        if(parentIdRaw != null && parentIdRaw > 0){
            return IdTypeHandler.encode(parentIdRaw);
        }
        return parentId;
    }

    public Long getParentIdRaw() {
        if(parentId !=null && !"".equals(parentId)){
            return IdTypeHandler.decode(parentId);
        }
        return parentIdRaw;
    }
}
