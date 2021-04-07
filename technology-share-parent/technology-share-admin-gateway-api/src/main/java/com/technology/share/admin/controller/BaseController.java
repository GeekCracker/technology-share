package com.technology.share.admin.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.technology.share.domain.BaseEntity;
import com.technology.share.handler.IdTypeHandler;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.BaseService;
import com.technology.share.utils.GenericsUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * BaseController
 */
public abstract class BaseController<T extends BaseEntity,S extends BaseService<T>>{

    /**当前Service*/
    @Autowired
    protected S service;

    /**当前实体类class对象*/
    protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());

    /**
     * 增加或修改
     * @param entity 传入需要添加或修改的实体
     * @return 返回操作信息
     */
    @PostMapping("save")
    public ResponseResult save(@RequestBody T entity){
        return ResponseResult.ok(service.saveOrUpdate(entity));
    }
    /**
     * 根据ID查询
     * @param id 传入ID
     * @return 返回结果
     */
    @RequestMapping("queryById")
    public ResponseResult queryById(String id){
        return ResponseResult.ok(service.getById(IdTypeHandler.decode(id)));
    }

    /**
     * 根据ID删除
     * @param id 传入ID
     * @return 返回操作信息
     */
    @RequestMapping("deleteById")
    public ResponseResult deleteById(String id){
        return ResponseResult.ok(service.removeById(IdTypeHandler.decode(id)));
    }

    /**
     * 批量删除
     * @param deleteList 传入json格式的需要删除的列表数据
     * @return 返回操作信息
     */
    @RequestMapping("deleteBatchById")
    public ResponseResult deleteBatchById(String deleteList){
        JSONArray jsonArray = JSONObject.parseArray(deleteList);
        List<Long> list = new LinkedList<>();
        for(Object obj : jsonArray.toArray()){
            Map<String,Object> map = (Map<String, Object>) obj;
            list.add(IdTypeHandler.decode(String.valueOf(map.get("id"))));
        }
        return ResponseResult.ok(service.removeByIds(list));
    }


    /**
     * 查询数据列表（不带分页）
     * @param request 传入request请求
     * @return 返回数据列表
     */
    @RequestMapping("queryData")
    public ResponseResult queryData(HttpServletRequest request){
        return ResponseResult.ok(service.list(getQueryWrapper(request)));
    }


    /**
     * 查询数据列表（带分页）
     * @param request 传入request请求
     * @return 返回分页数据
     */
    @PostMapping("queryPageData")
    public ResponseResult queryPageData(HttpServletRequest request){
        return ResponseResult.ok(service.page(getPage(request).addOrder(OrderItem.desc("create_time")),getQueryWrapper(request)));
    }

    /**
     * 根据请求获取请求条件
     * @param request 传入request请求
     * @return 返回请求条件
     */
    protected QueryWrapper<T> getQueryWrapper(HttpServletRequest request){
        return new QueryWrapper(getQueryParam(request).toJavaObject(this.entityClass));
    }

    protected <V> QueryWrapper<V> getQueryWrapper(HttpServletRequest request,Class<V> clazz){
        return new QueryWrapper<>(getQueryParam(request).toJavaObject(clazz));
    }

    /**
     * 获取分页对象
     * @param request 传入request请求
     * @return 返回分页请求对象
     */
    protected Page<T> getPage(HttpServletRequest request){
        JSONObject queryParams = getQueryParam(request);
        Long pageNum = queryParams.getLong("pageNum");
        Long pageSize = queryParams.getLong("pageSize");
        pageNum = pageNum != null ? pageNum : 1L;
        pageSize = pageSize != null ? pageSize : 10L;
        return new Page<>(pageNum,pageSize);
    }

    /**
     * 获取当前页码
     * @param request 传入请求参数
     * @return 返回当前页码
     */
    protected Long getPageNum(HttpServletRequest request){
        JSONObject queryParams = getQueryParam(request);
        Long pageNum = queryParams.getLong("pageNum");
        return pageNum != null ? pageNum : 1L;
    }

    /**
     * 获取当前每页显示条数
     * @param request 传入请求参数
     * @return 返回每页显示条数
     */
    protected Long getPageSize(HttpServletRequest request){
        JSONObject queryParams = getQueryParam(request);
        Long pageSize = queryParams.getLong("pageSize");
        return pageSize != null ? pageSize : 10L;
    }

    /**
     * 根据请求获取请求条件
     * @param request 传入request请求
     * @return 返回请求条件
     */
    protected JSONObject getQueryParam(HttpServletRequest request){
        //组装查询条件
        String str, wholeStr = "";
        try {
            BufferedReader br = request.getReader();
            while((str = br.readLine()) != null){
                wholeStr += str;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        JSONObject queryParam = JSONObject.parseObject(wholeStr);
        if(queryParam == null){
            queryParam = new JSONObject();
        }
        return queryParam;
    }
}
