package com.technology.share.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.technology.share.domain.Menu;
import com.technology.share.mapper.MenuMapper;
import com.technology.share.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service("FrontMenuService")
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<Menu> menuTree() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        List<Menu> menus = super.list(queryWrapper);
        if(menus != null && !menus.isEmpty()){
            List<Menu> top = menus.stream().filter(item->item.getParentIdRaw() == null || item.getParentIdRaw() == 0).collect(Collectors.toList());
            deep(top,menus);
            return top;
        }else {
            return new LinkedList<>();
        }
    }

    private void deep(List<Menu> menuTree,List<Menu> all){
        if(!menuTree.isEmpty()){
            for(Menu menu : menuTree){
                List<Menu> children = all.stream().filter(item->item.getParentIdRaw() != null && item.getParentIdRaw().equals(menu.getIdRaw())).collect(Collectors.toList());
                deep(children,all);
                menu.setChildren(children);
            }
        }
    }
}
