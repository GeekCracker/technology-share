package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 图标实体类
 */
@TableName("t_icon")
@Data
public class Icon extends BaseEntity {

    private String iconClass;
}
