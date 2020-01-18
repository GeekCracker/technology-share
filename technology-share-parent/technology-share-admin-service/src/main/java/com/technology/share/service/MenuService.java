package com.technology.share.service;

import com.technology.share.domain.Menu;

import java.util.List;

public interface MenuService extends BaseService<Menu>{
    /**
     * 获取菜单树
     * @return
     */
    List<Menu> menuTree();
}
