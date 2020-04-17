package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description: 数据库表
 * @author: 朱俊亮
 * @time: 2020/4/7 16:25
 */
@TableName("t_table")
@Data
public class Table extends BaseEntity {

    /**数据库表名*/
    private String tableName;
}
