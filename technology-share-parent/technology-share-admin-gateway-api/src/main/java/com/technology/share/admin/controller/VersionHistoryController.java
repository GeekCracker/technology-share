package com.technology.share.admin.controller;

import com.technology.share.domain.VersionHistory;
import com.technology.share.handler.IdTypeHandler;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.VersionHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 版本更新历史记录Controller
 * @author: 朱俊亮
 * @time: 2021-03-26 11:12
 */
@RestController("AdminVersionHistoryController")
@RequestMapping("/admin/versionHistory")
public class VersionHistoryController extends BaseController<VersionHistory, VersionHistoryService> {

    /**
     * 发布操作
     * @param id 传入历史记录ID
     * @return 返回操作信息
     */
    @GetMapping("/deploy/{id}/{serviceId}")
    public ResponseResult deploy(@PathVariable("id") String id,@PathVariable("serviceId") String serviceId){
        return service.deploy(IdTypeHandler.decode(id),IdTypeHandler.decode(serviceId));
    }
}
