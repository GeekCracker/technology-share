package com.technology.share.service.impl;

import com.technology.share.domain.view.VDatabase;
import com.technology.share.mapper.VDatabaseMapper;
import com.technology.share.service.VDatabaseService;
import org.springframework.stereotype.Service;

/**
 * @description: 数据库视图Service实现
 * @author: 朱俊亮
 * @time: 2020/4/7 17:46
 */
@Service("AdminVDatabaseService")
public class VDatabaseServiceImpl extends BaseServiceImpl<VDatabaseMapper, VDatabase> implements VDatabaseService {
}
