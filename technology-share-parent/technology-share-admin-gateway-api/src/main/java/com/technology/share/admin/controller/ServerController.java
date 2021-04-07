package com.technology.share.admin.controller;

import com.technology.share.domain.Server;
import com.technology.share.service.ServerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 服务器信息Controller
 * @author: 朱俊亮
 * @time: 2021-04-07 17:07
 */
@RestController("AdminServerController")
@RequestMapping("/admin/server")
public class ServerController extends BaseController<Server, ServerService> {
}
