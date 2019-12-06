package com.technology.share.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.technology.share.domain.ExtCss;
import com.technology.share.domain.ExtCssRole;
import com.technology.share.handler.IdTypeHandler;
import com.technology.share.mapper.ExtCssMapper;
import com.technology.share.service.ExtCssRoleService;
import com.technology.share.service.ExtCssService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service("AdminExtCssService")
public class ExtCssServiceImpl extends BaseServiceImpl<ExtCssMapper, ExtCss> implements ExtCssService {

    @Autowired
    private ExtCssRoleService extCssRoleService;

    @Override
    public boolean saveOrUpdate(ExtCss entity) {
        super.saveOrUpdate(entity);
        String roleIds = entity.getRoleIds();
        if(StringUtils.isNotBlank(roleIds)){
            //删除CSS所有角色
            UpdateWrapper<ExtCssRole> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("ext_css_id",entity.getIdRaw());
            extCssRoleService.remove(updateWrapper);
            //添加角色
            List<String> list = JSONObject.parseArray(roleIds,String.class);
            List<ExtCssRole> extCssRoles = new LinkedList<>();
            for(String roleId : list){
                ExtCssRole extCssRole = new ExtCssRole();
                extCssRole.setExtCssId(entity.getIdRaw());
                extCssRole.setRoleId(IdTypeHandler.decode(roleId));
                extCssRoles.add(extCssRole);
            }
            return extCssRoleService.saveBatch(extCssRoles);
        }
        return false;
    }

    @Override
    public boolean removeById(Serializable id) {
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        map.put("ext_css_id",id);
        extCssRoleService.removeByMap(map);
        return super.removeById(id);
    }
}
