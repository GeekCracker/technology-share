package com.technology.share.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.technology.share.domain.ExtJs;
import com.technology.share.domain.ExtJsRole;
import com.technology.share.handler.IdTypeHandler;
import com.technology.share.mapper.ExtJsMapper;
import com.technology.share.service.ExtJsRoleService;
import com.technology.share.service.ExtJsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service("AdminExtJsService")
public class ExtJsServiceImpl extends BaseServiceImpl<ExtJsMapper, ExtJs> implements ExtJsService {

    @Autowired
    private ExtJsRoleService extJsRoleService;

    @Override
    public boolean saveOrUpdate(ExtJs entity) {
        super.saveOrUpdate(entity);
        String roleIds = entity.getRoleIds();
        if(StringUtils.isNotBlank(roleIds)){
            //删除JS所有角色
            UpdateWrapper<ExtJsRole> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("ext_js_id",entity.getIdRaw());
            extJsRoleService.remove(updateWrapper);
            //添加角色
            List<String> list = JSONObject.parseArray(roleIds,String.class);
            List<ExtJsRole> extJsRoles = new LinkedList<>();
            for(String roleId : list){
                ExtJsRole extJsRole = new ExtJsRole();
                extJsRole.setExtJsId(entity.getIdRaw());
                extJsRole.setRoleId(IdTypeHandler.decode(roleId));
                extJsRoles.add(extJsRole);
            }
            return extJsRoleService.saveBatch(extJsRoles);
        }
        return false;
    }

    @Override
    public boolean removeById(Serializable id) {
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        map.put("ext_js_id",id);
        extJsRoleService.removeByMap(map);
        return super.removeById(id);
    }
}
