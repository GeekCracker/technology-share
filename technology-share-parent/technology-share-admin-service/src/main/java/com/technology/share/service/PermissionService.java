package com.technology.share.service;

import com.technology.share.domain.Permission;

import java.util.List;

public interface PermissionService extends BaseService<Permission> {

    /**
     * 获取权限树
     * @return 返回权限树
     */
    List<Permission> permissionTree();
}
