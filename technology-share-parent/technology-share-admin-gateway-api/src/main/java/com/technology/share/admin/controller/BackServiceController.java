package com.technology.share.admin.controller;

import com.technology.share.domain.BackService;
import com.technology.share.service.BackServiceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 后端服务管理
 * @author: 朱俊亮
 * @time: 2021-03-27 14:35
 */
@RestController("AdminBackServiceController")
@RequestMapping("/admin/backService")
public class BackServiceController extends BaseController<BackService, BackServiceService> {
}
