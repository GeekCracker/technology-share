package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 文章实体类
 */
@TableName("t_article")
public class Article extends BaseEntity {

    /**标题*/
    private String title;

    /**文章类型ID*/
    private Long typeId;

    /**内容*/
    private String content;

    /**作者*/
    private String author;

    /**状态：0：草稿1：发布*/
    private Byte status;

    /**是否启用：0：禁用1：启用*/
    private Boolean enable;

    /**是否前十条*/
    private Boolean topTen;

    /**发布时间*/
    private Date publishTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getTopTen() {
        return topTen;
    }

    public void setTopTen(Boolean topTen) {
        this.topTen = topTen;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}
