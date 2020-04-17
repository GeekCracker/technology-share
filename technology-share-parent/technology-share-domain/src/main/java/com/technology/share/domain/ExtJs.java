package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 外部JS实体类
 */
@TableName("t_ext_js")
@Data
public class ExtJs extends BaseEntity {

    /**外部JS链接*/
    private String url;

    /**描述信息*/
    private String description;

    /**角色ID列表*/
    @TableField(exist = false)
    private String roleIds;

    /**排序字段*/
    private Integer sort;

    /**是否启用（0:禁用1:启用）*/
    private Boolean enable;
}
