package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * JS链接与角色关联实体类
 */
@TableName("t_ext_js_role")
@Data
public class ExtJsRole extends BaseEntity {

    private Long extJsId;

    private Long roleId;
}
