package com.technology.share.service.impl;

import com.technology.share.domain.Server;
import com.technology.share.mapper.ServerMapper;
import com.technology.share.service.ServerService;
import org.springframework.stereotype.Service;

/**
 * @description: 服务器信息Service实现
 * @author: 朱俊亮
 * @time: 2021-04-07 17:05
 */
@Service
public class ServerServiceImpl extends BaseServiceImpl<ServerMapper, Server> implements ServerService {
}
