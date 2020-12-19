package com.technology.share.service;

import com.technology.share.domain.Sql;
import com.technology.share.response.ResponseResult;

/**
 * @description: SQLService
 * @author: 朱俊亮
 * @time: 2020/4/7 18:58
 */
public interface SqlService extends BaseService<Sql> {

    /**
     * 执行SQL语句
     * @param id 传入SQL语句ID
     * @return 返回操作信息
     */
    ResponseResult process(Long id);
}
