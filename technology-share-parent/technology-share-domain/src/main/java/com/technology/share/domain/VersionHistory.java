package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description: 版本更新历史
 * @author: 朱俊亮
 * @time: 2021-03-26 10:41
 */
@Data
@TableName("t_version_history")
public class VersionHistory extends BaseEntity{

    /**服务名称*/
    @TableField("service_name")
    private String serviceName;

    /**版本号*/
    @TableField("version")
    private String version;

    /**本地项目包文件下载路径*/
    @TableField("download_path")
    private String downloadPath;

    /**部署项目路径*/
    @TableField("publish_path")
    private String publishPath;

    /**版本更新内容描述*/
    @TableField("memo")
    private String memo;

    /**状态，-1：发布失败0：待发布1：发布中2：发布成功*/
    @TableField("status")
    private Integer status;

    /**发布耗时（单位：毫秒）*/
    @TableField("time_consuming")
    private Integer timeConsuming;
}
