package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * 文章类别实体类
 */
@TableName("t_article_type")
public class ArticleType extends BaseEntity {

    /**类别名称*/
    private String typeName;

    /**父级类别ID*/
    private Long parentId;

    /**子级类别列表*/
    private List<ArticleType> children;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<ArticleType> getChildren() {
        return children;
    }

    public void setChildren(List<ArticleType> children) {
        this.children = children;
    }
}
