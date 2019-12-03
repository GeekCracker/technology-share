package com.technology.share.admin.controller;

import com.technology.share.domain.Role;
import com.technology.share.handler.IdTypeHandler;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.RoleService;
import com.technology.share.service.VRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("AdminRoleController")
@RequestMapping("/admin/role")
public class RoleController extends BaseController<Role, RoleService> {

    @Autowired
    private VRoleService vRoleService;

    /**
     * 根据ID查询
     * @param id 传入ID
     * @return 返回结果
     */
    @Override
    @RequestMapping("queryById")
    public ResponseResult queryById(String id) {
        return ResponseResult.ok(vRoleService.getById(IdTypeHandler.decode(id)));
    }
}
