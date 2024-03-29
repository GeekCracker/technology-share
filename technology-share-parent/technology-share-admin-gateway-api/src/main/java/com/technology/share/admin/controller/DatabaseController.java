package com.technology.share.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.technology.share.domain.Database;
import com.technology.share.domain.view.VDatabase;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.DatabaseService;
import com.technology.share.service.VDatabaseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 数据库Controller
 * @author: 朱俊亮
 * @time: 2020/4/7 17:40
 */
@RestController("AdminDatabaseController")
@RequestMapping("/admin/database")
@Api(tags = "数据库管理")
public class DatabaseController extends BaseController<Database, DatabaseService> {

    @Autowired
    private VDatabaseService vDatabaseService;

    @Override
    public ResponseResult queryPageData( HttpServletRequest request) {
        JSONObject queryParams = getQueryParam(request);
        QueryWrapper<VDatabase> queryWrapper = getQueryWrapper(queryParams,VDatabase.class);
        return ResponseResult.ok(vDatabaseService.page(new Page<>(getPageNum(queryParams),getPageSize(queryParams)), queryWrapper));
    }
}
