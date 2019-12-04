package com.technology.share.service.impl;

import com.technology.share.domain.view.VUser;
import com.technology.share.mapper.VUserMapper;
import com.technology.share.service.VUserService;
import org.springframework.stereotype.Service;

@Service("AdminVUserService")
public class VUserServiceImpl extends BaseServiceImpl<VUserMapper, VUser> implements VUserService {
}
