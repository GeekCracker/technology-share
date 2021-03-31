package com.technology.share.service.impl;

import com.technology.share.domain.VersionHistory;
import com.technology.share.mapper.VersionHistoryMapper;
import com.technology.share.service.VersionHistoryService;
import org.springframework.stereotype.Service;

/**
 * @description: 版本更新历史Service实现
 * @author: 朱俊亮
 * @time: 2021-03-26 11:10
 */
@Service
public class VersionHistoryServiceImpl extends BaseServiceImpl<VersionHistoryMapper, VersionHistory> implements VersionHistoryService {
}
