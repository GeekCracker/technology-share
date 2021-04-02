package com.technology.share.service;

import com.technology.share.domain.VersionHistory;
import com.technology.share.response.ResponseResult;

/**
 * @description: 版本更新历史记录Service
 * @author: 朱俊亮
 * @time: 2021-03-26 11:09
 */
public interface VersionHistoryService extends BaseService<VersionHistory> {

    /**
     * 版本发布操作
     * @param id 传入历史版本记录ID
     * @param serviceId 服务器ID
     * @return 返回操作信息
     */
    ResponseResult deploy(Long id,Long serviceId);
}
