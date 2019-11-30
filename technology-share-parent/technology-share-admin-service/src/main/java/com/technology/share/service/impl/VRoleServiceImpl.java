package com.technology.share.service.impl;

import com.technology.share.domain.Permission;
import com.technology.share.domain.view.VRole;
import com.technology.share.mapper.VRoleMapper;
import com.technology.share.service.VRoleService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VRoleServiceImpl extends BaseServiceImpl<VRoleMapper, VRole> implements VRoleService {

    @Override
    public VRole getById(Serializable id) {
        VRole vRole = super.getById(id);
        if(vRole != null){
            permissionTree(vRole);
        }
        return vRole;
    }

    private List<Permission> permissionTree(VRole vRole){
        List<Permission> permissions = vRole.getPermissionTree();
        if(permissions != null && !permissions.isEmpty()){
            List<Permission> topPermission = permissions.stream().filter(item->item.getParentIdRow()==null||item.getParentIdRow()==0).collect(Collectors.toList());
            if(!topPermission.isEmpty()){
                deep(topPermission,permissions);
            }else {
                return topPermission;
            }
        }else {
            permissions = new LinkedList<Permission>();
        }
        return permissions;
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
