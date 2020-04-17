package com.technology.share.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.technology.share.domain.Permission;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.PermissionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseResult queryPageData(@RequestParam(defaultValue = "1") Long pageNum, @RequestParam(defaultValue = "10") Long pageSize, HttpServletRequest request) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper(JSONObject.parseObject(JSONObject.toJSONString(getQueryParam(request)),Permission.class));
        queryWrapper.orderByAsc("permission_sort");
        return ResponseResult.ok(service.page(new Page<Permission>(pageNum,pageSize),queryWrapper));
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
