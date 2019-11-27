package com.technology.share.domain;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {

    /**主键ID*/
    private Long id;

    /**创建人*/
    private String createBy;

    /**创建时间*/
    private Date createTime;

    /**修改人*/
    private String updateBy;

    /**修改时间*/
    private Date updateTime;

    /**是否删除(1:不删除0:删除)*/
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
