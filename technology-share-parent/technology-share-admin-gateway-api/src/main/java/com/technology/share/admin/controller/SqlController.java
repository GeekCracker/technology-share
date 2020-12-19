package com.technology.share.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.technology.share.domain.Sql;
import com.technology.share.domain.view.VSql;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.SqlService;
import com.technology.share.service.VSqlService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * @description: SQLController
 * @author: 朱俊亮
 * @time: 2020/4/7 19:00
 */
@RestController("AdminSqlController")
@RequestMapping("/admin/sql")
@Api(tags = "SQL语句管理")
public class SqlController extends BaseController<Sql, SqlService> {

    @Autowired
    private VSqlService vSqlService;

    @Override
    @RequestMapping("queryPageData")
    public ResponseResult queryPageData(@RequestParam(value = "pageNum",defaultValue = "1")Long pageNum, @RequestParam(value = "pageSize",defaultValue = "10")Long pageSize, HttpServletRequest request) {
        QueryWrapper<VSql> queryWrapper = new QueryWrapper<>(JSONObject.parseObject(JSONObject.toJSONString(getQueryParam(request)), VSql.class));
        return ResponseResult.ok(vSqlService.page(new Page<>(pageNum,pageSize),queryWrapper));
    }

    /**
     * 执行SQL
     * @param id 传入SQL语句ID
     * @return 返回操作信息
     */
    @RequestMapping("process")
    public ResponseResult process(@NotNull(message = "ID不能为空") Long id){
        return service.process(id);
    }
}
