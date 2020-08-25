package com.technology.share.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.technology.share.domain.Sql;
import com.technology.share.domain.SqlRole;
import com.technology.share.handler.IdTypeHandler;
import com.technology.share.mapper.SqlMapper;
import com.technology.share.service.SqlRoleService;
import com.technology.share.service.SqlService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @description: SQLService实现
 * @author: 朱俊亮
 * @time: 2020/4/7 18:58
 */
@Service
public class SqlServiceImpl extends BaseServiceImpl<SqlMapper, Sql> implements SqlService {

    @Autowired
    private SqlRoleService sqlRoleService;

    @Override
    public boolean saveOrUpdate(Sql entity) {
        super.saveOrUpdate(entity);
        String roleIds = entity.getRoleIds();
        if(StringUtils.isNotBlank(roleIds)){
            //删除JS所有角色
            UpdateWrapper<SqlRole> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("sql_id",entity.getIdRaw());
            sqlRoleService.remove(updateWrapper);
            //添加角色
            List<String> list = JSONObject.parseArray(roleIds,String.class);
            List<SqlRole> sqlRoles = new LinkedList<>();
            for(String roleId : list){
                SqlRole sqlRole = new SqlRole();
                sqlRole.setSqlId(entity.getIdRaw());
                sqlRole.setRoleId(IdTypeHandler.decode(roleId));
                sqlRoles.add(sqlRole);
            }
            return sqlRoleService.saveBatch(sqlRoles);
        }
        return false;
    }

    @Override
    public boolean removeById(Serializable id) {
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        map.put("sql_id",id);
        sqlRoleService.removeByMap(map);
        return super.removeById(id);
    }
}
