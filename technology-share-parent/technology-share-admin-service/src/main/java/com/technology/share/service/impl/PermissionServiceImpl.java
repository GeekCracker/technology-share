package com.technology.share.service.impl;

import com.technology.share.domain.Permission;
import com.technology.share.mapper.PermissionMapper;
import com.technology.share.service.PermissionService;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper, Permission> implements PermissionService {
}
