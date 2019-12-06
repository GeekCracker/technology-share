package com.technology.share.service.impl;

import com.technology.share.domain.Icon;
import com.technology.share.mapper.IconMapper;
import com.technology.share.service.IconService;
import org.springframework.stereotype.Service;

@Service("AdminIconService")
public class IconServiceImpl extends BaseServiceImpl<IconMapper, Icon> implements IconService {
}
