package com.technology.share.admin.controller;

import com.technology.share.domain.Icon;
import com.technology.share.service.IconService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 图标Controller
 */
@RestController("AdminIconController")
@RequestMapping("/admin/icon")
public class IconController extends BaseController<Icon,IconService> {
}
