package com.technology.share.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.technology.share.domain.RolePermission;
import com.technology.share.mapper.RolePermissionMapper;
import com.technology.share.service.RolePermissionService;
import org.springframework.stereotype.Service;

@Service("AdminRolePermissionService")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
}
