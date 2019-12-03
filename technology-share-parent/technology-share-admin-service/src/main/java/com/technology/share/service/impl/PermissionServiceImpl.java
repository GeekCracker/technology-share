package com.technology.share.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.technology.share.domain.Permission;
import com.technology.share.mapper.PermissionMapper;
import com.technology.share.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper, Permission> implements PermissionService {


    @Override
    public List<Permission> permissionTree() {
        //获取所有的权限
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("permission_sort");
        List<Permission> all = this.list(queryWrapper);
        if(!all.isEmpty()){
            List<Permission> top = all.stream().filter(item->item.getParentIdRow() ==null || item.getParentIdRow()==0).collect(Collectors.toList());
            deep(top,all);
            return top;
        }else {
            return new LinkedList<Permission>();
        }
    }
    private void deep(List<Permission> treeList,List<Permission> all){
        for(Permission permission : treeList){
            List<Permission> children = all.stream().filter(item->permission.getIdRaw().equals(item.getParentIdRow())).collect(Collectors.toList());
            if(!children.isEmpty()){
                all.removeAll(children);
                deep(children,all);
            }
            permission.setChildren(children);
        }
    }

}
