package com.technology.share.admin.controller;

import com.technology.share.domain.VersionHistory;
import com.technology.share.service.VersionHistoryService;
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
}
