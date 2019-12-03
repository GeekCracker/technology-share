package com.technology.share.service;

import com.technology.share.domain.User;

public interface UserService extends BaseService<User> {

    /**
     * 根据ID禁用或启用用户
     * @param id  传入用户ID
     */
    void enableDisable(Long id);
}
