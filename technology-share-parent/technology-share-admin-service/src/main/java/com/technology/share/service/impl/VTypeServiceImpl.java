package com.technology.share.service.impl;

import com.technology.share.domain.view.VType;
import com.technology.share.mapper.VTypeMapper;
import com.technology.share.service.VTypeService;
import org.springframework.stereotype.Service;

@Service("AdminVTypeService")
public class VTypeServiceImpl extends BaseServiceImpl<VTypeMapper, VType> implements VTypeService {
}
