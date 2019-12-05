package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.technology.share.handler.IdTypeHandler;

import java.util.List;

/**
 * 类别实体类
 */
@TableName("t_type")
public class Type extends BaseEntity {

    /**类别名称*/
    private String typeName;

    /**父级类别ID*/
    @TableField(exist = false)
    private String parentId;

    @TableField("parent_id")
    @JsonIgnore
    private Long parentIdRaw;

    /**子级类别列表*/
    @TableField(exist = false)
    private List<Type> children;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
        if(parentId != null && !"".equals(parentId)){
            return IdTypeHandler.decode(parentId);
        }
        return parentIdRaw;
    }

    public void setParentIdRaw(Long parentIdRaw) {
        this.parentIdRaw = parentIdRaw;
    }

    public List<Type> getChildren() {
        return children;
    }

    public void setChildren(List<Type> children) {
        this.children = children;
    }
}
