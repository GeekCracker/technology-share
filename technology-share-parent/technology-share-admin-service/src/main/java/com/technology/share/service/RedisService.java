package com.technology.share.service;

import com.technology.share.domain.Redis;

import java.util.List;
import java.util.Map;

public interface RedisService extends BaseService<Redis> {

    /**
     * 获取Redis数据
     * @param queryParam 传入Redis配置信息
     * @return 返回Redis数据
     */
    List<Map<String,Object>> queryRedisData(Map<String,Object> queryParam);
}
