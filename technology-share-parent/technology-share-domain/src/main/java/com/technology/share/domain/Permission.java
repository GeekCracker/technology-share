package com.technology.share.domain;

import java.util.List;

public class Permission extends BaseEntity {

    /**权限名*/
    private String permissionName;

    /**权限位*/
    private String permissionBit;

    /**父级权限*/
    private Long parentPermission;

    /**子级权限*/
    private List<Permission> children;

}
