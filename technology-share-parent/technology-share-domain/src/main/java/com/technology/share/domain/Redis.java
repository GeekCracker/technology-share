package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Redis链接实体类
 */
@TableName("t_redis")
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
