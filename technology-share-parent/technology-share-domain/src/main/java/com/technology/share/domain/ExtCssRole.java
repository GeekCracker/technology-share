package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * CSS链接与角色关联实体类
 */
@TableName("t_ext_css_role")
@Data
public class ExtCssRole extends BaseEntity {

    private Long extCssId;

    private Long roleId;
}
