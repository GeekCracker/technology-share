package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 外部CSS实体类
 */
@TableName("t_ext_css")
@Data
public class ExtCss extends BaseEntity{

    /**外部CSS链接*/
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
