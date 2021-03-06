package com.technology.share.domain.view;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.technology.share.domain.BaseEntity;
import com.technology.share.handler.IdTypeHandler;
import lombok.Data;

/**
 * 类别视图
 */
@TableName("v_type")
@Data
public class VType extends BaseEntity {

    /**类别名称*/
    private String typeName;

    /**父级类别ID*/
    @TableField(exist = false)
    private String parentId;

    @TableField("parent_id")
    @JsonIgnore
    private Long parentIdRaw;

    private String parentTypeName;

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

    public Long getParentIdRaw() {
        if(parentId != null && !"".equals(parentId)){
            return IdTypeHandler.decode(parentId);
        }
        return parentIdRaw;
    }
}
