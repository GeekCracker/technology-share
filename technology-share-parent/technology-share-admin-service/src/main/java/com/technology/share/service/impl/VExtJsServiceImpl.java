package com.technology.share.service.impl;

import com.technology.share.domain.view.VExtJs;
import com.technology.share.mapper.VExtJsMapper;
import com.technology.share.service.VExtJsService;
import org.springframework.stereotype.Service;

@Service("AdminVExtJsService")
public class VExtJsServiceImpl extends BaseServiceImpl<VExtJsMapper, VExtJs> implements VExtJsService {
}
