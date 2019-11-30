package com.technology.share.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.technology.share.domain.Permission;
import com.technology.share.handler.IdTypeHandler;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 权限Controller
 */
@RestController("AdminPermissionController")
@RequestMapping("/admin/permission")
public class PermissionController extends BaseController<Permission, PermissionService> {

    @Autowired
    private PermissionService permissionService;


   /* @Override
    protected Wrapper<Permission> getQueryWrapper(HttpServletRequest request) {
        //组装查询条件
        Map<String,Object> map = new LinkedHashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String paramName = parameterNames.nextElement();
            if("parentId".equals(paramName)){
                String parentId = request.getParameter(paramName);
                map.put("parentIdRow", IdTypeHandler.decode(parentId));
            }else {
                map.put(paramName,request.getParameter(paramName));
            }
        }
        System.out.println(map);
        return new QueryWrapper(JSONObject.parseObject(JSONObject.toJSONString(map),this.entityClass));
    }*/

    /**
     * 获取权限树
     * @return 返回权限树
     */
    @RequestMapping("permissionTree")
    public ResponseResult permissionTree(){
        return ResponseResult.ok(permissionService.permissionTree());
    }
}
