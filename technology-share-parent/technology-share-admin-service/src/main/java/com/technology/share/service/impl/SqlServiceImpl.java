package com.technology.share.service.impl;

import com.technology.share.domain.Sql;
import com.technology.share.mapper.SqlMapper;
import com.technology.share.service.SqlService;
import org.springframework.stereotype.Service;

/**
 * @description: SQLService实现
 * @author: 朱俊亮
 * @time: 2020/4/7 18:58
 */
@Service
public class SqlServiceImpl extends BaseServiceImpl<SqlMapper, Sql> implements SqlService {
}
