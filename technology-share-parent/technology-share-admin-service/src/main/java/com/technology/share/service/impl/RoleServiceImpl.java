package com.technology.share.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.technology.share.domain.Role;
import com.technology.share.domain.RolePermission;
import com.technology.share.handler.IdTypeHandler;
import com.technology.share.mapper.RoleMapper;
import com.technology.share.service.RolePermissionService;
import com.technology.share.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RolePermissionService rolePermissionService;

    @Override
    public boolean saveOrUpdate(Role entity) {
        super.saveOrUpdate(entity);
        String pIds = entity.getPIds();
        if(StringUtils.isNotBlank(pIds)){
            //删除角色原有权限
            UpdateWrapper<RolePermission> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("role_id",entity.getIdRaw());
            rolePermissionService.remove(updateWrapper);
            //添加权限
            List<String> list = JSONObject.parseArray(pIds,String.class);
            List<RolePermission> rolePermissions = new LinkedList<>();
            for(String pId : list){
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(entity.getIdRaw());
                rolePermission.setPermissionId(IdTypeHandler.decode(pId));
                rolePermissions.add(rolePermission);
            }
            return rolePermissionService.saveBatch(rolePermissions);
        }
        return false;
    }
}
