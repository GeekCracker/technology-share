package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description: 后台服务
 * @author: 朱俊亮
 * @time: 2021-03-26 11:28
 */
@Data
@TableName("t_back_service")
public class BackService extends BaseEntity {

    /**服务名称*/
    @TableField("service_name")
    private String serviceName;

    /**分组ID*/
    @TableField("group_id")
    private String groupId;

    /**唯一ID*/
    @TableField("artifact_id")
    private String artifactId;

    /**版本号*/
    @TableField("version")
    private String version;
}
