package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 文件信息
 */
@TableName("t_file_info")
public class FileInfo extends BaseEntity {

    /**原始文件名称*/
    private String originalName;

    /**文件大小，单位：字节*/
    private Long size;

    /**HASH散列值*/
    @JsonIgnore
    private String hash;

    /**MD5散列值*/
    @JsonIgnore
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

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getSmallUri() {
        return smallUri;
    }

    public void setSmallUri(String smallUri) {
        this.smallUri = smallUri;
    }

    public String getBigUri() {
        return bigUri;
    }

    public void setBigUri(String bigUri) {
        this.bigUri = bigUri;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }
}
