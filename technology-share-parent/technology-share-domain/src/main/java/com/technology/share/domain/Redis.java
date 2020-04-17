package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Redis链接实体类
 */
@TableName("t_redis")
@Data
public class Redis extends BaseEntity {

    /**Redis链接地址*/
    private String host;

    /**Redis端口号*/
    private Integer port;

    /**Redis链接用户名*/
    private String username;

    /**Redis链接密码*/
    private String password;

    /**是否启用*/
    private Boolean enable;
}
