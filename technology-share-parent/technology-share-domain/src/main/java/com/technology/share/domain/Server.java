package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description: 服务器信息实体类
 * @author: 朱俊亮
 * @time: 2021-04-07 11:53
 */
@Data
@TableName("b_server")
public class Server extends BaseEntity{

    /**服务器名称*/
    @TableField("server_name")
    private String serverName;

    /**服务器IP地址*/
    @TableField("ip")
    private String ip;

    /**服务器登陆用户名*/
    @TableField("username")
    private String username;

    /**服务器登陆密码*/
    @TableField("password")
    private String password;

    /**描述信息*/
    @TableField("memo")
    private String memo;
}
