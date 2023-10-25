package com.technology.share.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.technology.share.domain.Permission;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.PermissionService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限Controller
 */
@RestController("AdminPermissionController")
@RequestMapping("/admin/permission")
@Api(tags = "权限管理")
public class PermissionController extends BaseController<Permission, PermissionService> {

    @Override
    protected QueryWrapper<Permission> getQueryWrapper(JSONObject queryParams) {
        Permission permission = queryParams.toJavaObject(Permission.class);
        QueryWrapper<Permission> queryWrapper =new QueryWrapper<>();
        if(permission.getParentIdRaw() != null){
            queryWrapper.eq("parent_id", permission.getParentIdRaw());
        }
        if(StringUtils.isNotBlank(permission.getPermissionName())){
            queryWrapper.like("permission_name", permission.getPermissionName());
        }
        queryWrapper.orderByAsc("permission_sort");
        return queryWrapper;
    }

    /**
     * 获取权限树
     * @return 返回权限树
     */
    @RequestMapping("permissionTree")
    public ResponseResult permissionTree(){
        return ResponseResult.ok(service.permissionTree());
    }
}
