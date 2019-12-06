package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 图标实体类
 */
@TableName("t_icon")
public class Icon extends BaseEntity {

    private String iconClass;

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }
}
