package com.technology.share.front.controller;

import com.technology.share.domain.Menu;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单管理
 */
@RestController("FrontMenuController")
@RequestMapping("/front/menu")
public class MenuController extends BaseController<Menu, MenuService> {

    @Autowired
    private MenuService menuService;

    @RequestMapping("menuTree")
    public ResponseResult menuTree(){
        return ResponseResult.ok(menuService.menuTree());
    }
}
