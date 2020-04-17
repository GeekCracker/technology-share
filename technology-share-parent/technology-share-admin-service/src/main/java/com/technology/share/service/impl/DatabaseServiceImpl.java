package com.technology.share.service.impl;

import com.technology.share.domain.Database;
import com.technology.share.mapper.DatabaseMapper;
import com.technology.share.service.DatabaseService;
import org.springframework.stereotype.Service;

/**
 * @description: 数据库Service实现
 * @author: 朱俊亮
 * @time: 2020/4/7 17:39
 */
@Service("AdminDatabaseService")
public class DatabaseServiceImpl extends BaseServiceImpl<DatabaseMapper, Database> implements DatabaseService {
}
