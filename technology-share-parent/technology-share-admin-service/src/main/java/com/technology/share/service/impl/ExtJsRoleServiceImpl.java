package com.technology.share.service.impl;

import com.technology.share.domain.ExtJsRole;
import com.technology.share.mapper.ExtJsRoleMapper;
import com.technology.share.service.ExtJsRoleService;
import org.springframework.stereotype.Service;

@Service("AdminExtJsRoleService")
public class ExtJsRoleServiceImpl extends BaseServiceImpl<ExtJsRoleMapper, ExtJsRole> implements ExtJsRoleService {
}
