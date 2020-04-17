package com.technology.share.admin.controller;

import com.technology.share.domain.Menu;
import com.technology.share.service.MenuService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单管理
 */
@RestController("AdminMenuController")
@RequestMapping("/admin/menu")
@Api(tags = "菜单管理")
public class MenuController extends BaseController<Menu, MenuService> {
}
