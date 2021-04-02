package com.technology.share.service.impl;

import com.technology.share.domain.VersionHistory;
import com.technology.share.mapper.VersionHistoryMapper;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.VersionHistoryService;
import com.technology.share.utils.RemoteShellExecutorUtils;
import org.springframework.stereotype.Service;

/**
 * @description: 版本更新历史Service实现
 * @author: 朱俊亮
 * @time: 2021-03-26 11:10
 */
@Service
public class VersionHistoryServiceImpl extends BaseServiceImpl<VersionHistoryMapper, VersionHistory> implements VersionHistoryService {


    @Override
    public ResponseResult deploy(Long id,Long serviceId) {
        VersionHistory versionHistory = getById(id);
        if(versionHistory == null){
            return ResponseResult.fail("版本更新历史不存在");
        }
        //执行远程shell脚本


        RemoteShellExecutorUtils.exec("","","","");



        return null;
    }
}
