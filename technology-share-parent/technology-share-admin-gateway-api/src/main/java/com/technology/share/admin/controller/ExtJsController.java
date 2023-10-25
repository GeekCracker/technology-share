package com.technology.share.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.technology.share.domain.ExtJs;
import com.technology.share.domain.view.VExtJs;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.ExtJsService;
import com.technology.share.service.VExtJsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 外部JS Controller
 */
@RestController("AdminExtJsController")
@RequestMapping("/admin/ext/js")
@Api(tags = "扩展JS管理")
public class ExtJsController extends BaseController<ExtJs,ExtJsService> {

    @Autowired
    private VExtJsService vExtJsService;

    /**
     * 查询数据列表（带分页）
     * @param request 传入request请求
     * @return 返回分页数据
     */
    @Override
    public ResponseResult queryPageData(HttpServletRequest request) {
        JSONObject queryParams = getQueryParam(request);
        QueryWrapper<VExtJs> queryWrapper = getQueryWrapper(queryParams, VExtJs.class);
        return ResponseResult.ok(vExtJsService.page(new Page<>(getPageNum(queryParams),getPageSize(queryParams)), queryWrapper));
    }
}
