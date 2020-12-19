package com.technology.share.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.technology.share.domain.Database;
import com.technology.share.domain.Sql;
import com.technology.share.domain.SqlRole;
import com.technology.share.handler.IdTypeHandler;
import com.technology.share.mapper.SqlMapper;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.DatabaseService;
import com.technology.share.service.SqlRoleService;
import com.technology.share.service.SqlService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
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

    @Autowired
    private DatabaseService databaseService;

    @Override
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        map.put("sql_id",id);
        sqlRoleService.removeByMap(map);
        return super.removeById(id);
    }

    @Override
    public ResponseResult process(Long id) {
        Sql sql = getById(id);
        if(sql == null){
            return ResponseResult.fail("根据ID未查询到SQL语句记录");
        }
        Integer type = sql.getType();
        Long databaseId = sql.getDatabaseIdRaw();
        if(databaseId == null){
            return ResponseResult.fail("该SQL语句未分配所属数据库");
        }
        Database database = databaseService.getById(databaseId);
        if(database == null){
            return ResponseResult.fail("该SQL语句所属的数据库不存在");
        }
        String sqlStatement = sql.getSqlStatement();
        if(StringUtils.isBlank(sqlStatement)){
            return ResponseResult.fail("要执行的SQL语句为空");
        }
        switch (type){
            case 1:
                //如果是增加操作

                break;
            case 2:
                //如果是删除操作
                break;
            case 3:
                //如果是修改操作
                break;
            case 4:
                //如果是查询操作
                break;
            case 5:
                //如果是修改数据库结构操作
                break;
            case 6:
                //如果是设置用户权限操作
                break;
            default:
                return ResponseResult.fail("未知的SQL类型");
        }
        return ResponseResult.fail("执行失败");
    }
}
