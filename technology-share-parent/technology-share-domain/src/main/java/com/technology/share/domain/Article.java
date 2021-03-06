package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.technology.share.handler.IdTypeHandler;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 文章实体类
 */
@TableName("t_article")
@Data
public class Article extends BaseEntity {

    /**标题*/
    private String title;

    /**文章类型ID*/
    @TableField(exist = false)
    private String typeId;

    /**文章类型ID*/
    @TableField("type_id")
    @JsonIgnore
    private Long typeIdRaw;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    public String getTypeId() {
        if(typeIdRaw != null && typeIdRaw > 0){
           return IdTypeHandler.encode(typeIdRaw);
        }
        return typeId;
    }

    public Long getTypeIdRaw() {
        if(typeId != null && !"".equals(typeId)){
            return IdTypeHandler.decode(typeId);
        }
        return typeIdRaw;
    }
}
