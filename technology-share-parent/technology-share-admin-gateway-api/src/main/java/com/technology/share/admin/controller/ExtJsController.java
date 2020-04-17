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
import org.springframework.web.bind.annotation.RequestParam;
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
     * @param pageNum 当前页
     * @param pageSize 每页显示数据条数
     * @param request 传入request请求
     * @return 返回分页数据
     */
    @Override
    @RequestMapping("queryPageData")
    public ResponseResult queryPageData(@RequestParam(defaultValue = "1") Long pageNum, @RequestParam(defaultValue = "10") Long pageSize, HttpServletRequest request) {
        QueryWrapper<VExtJs> queryWrapper = new QueryWrapper(JSONObject.parseObject(JSONObject.toJSONString(getQueryParam(request)),VExtJs.class));
        return ResponseResult.ok(vExtJsService.page(new Page<VExtJs>(pageNum,pageSize),queryWrapper));
    }
}
