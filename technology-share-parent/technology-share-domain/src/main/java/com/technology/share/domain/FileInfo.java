package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 文件信息
 */
@TableName("t_file_info")
@Data
public class FileInfo extends BaseEntity {

    /**原始文件名称*/
    private String originalName;

    /**文件大小，单位：字节*/
    private Long size;

    /**HASH散列值*/
    private String hash;

    /**MD5散列值*/
    private String md5;

    /**缩略图URI*/
    private String smallUri;

    /**原始图URI*/
    private String bigUri;

    /**文件扩展名*/
    private String ext;

    /**UUID，用来作为文件服务器上的文件名*/
    private String uuid;

    /**占用次数*/
    private Integer useCount;
}
