package com.technology.share.domain;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_user")
public class User extends BaseEntity {

    private String userName;

    private String userPassword;

    private Long age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
