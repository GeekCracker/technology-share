package com.technology.share.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.technology.share.domain.ExtCss;
import com.technology.share.domain.view.VExtCss;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.ExtCssService;
import com.technology.share.service.VExtCssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 外部CSS Controller
 */
@RestController("AdminExtCssController")
@RequestMapping("/admin/ext/css")
public class ExtCssController extends BaseController<ExtCss, ExtCssService> {

    @Autowired
    private VExtCssService vExtCssService;

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
        QueryWrapper<VExtCss> queryWrapper = new QueryWrapper(JSONObject.parseObject(JSONObject.toJSONString(getQueryParam(request)),VExtCss.class));
        return ResponseResult.ok(vExtCssService.page(new Page<VExtCss>(pageNum,pageSize),queryWrapper));
    }
}
