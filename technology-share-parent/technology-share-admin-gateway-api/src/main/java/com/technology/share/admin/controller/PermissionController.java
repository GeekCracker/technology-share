package com.technology.share.admin.controller;

import com.technology.share.domain.Permission;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限Controller
 */
@RestController("AdminPermissionController")
@RequestMapping("/admin/permission")
public class PermissionController extends BaseController<Permission, PermissionService> {

    @Autowired
    private PermissionService permissionService;

    /**
     * 获取权限树
     * @return 返回权限树
     */
    @RequestMapping("permissionTree")
    public ResponseResult permissionTree(){
        return ResponseResult.ok(permissionService.permissionTree());
    }
}
