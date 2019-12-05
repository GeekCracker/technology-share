package com.technology.share.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.technology.share.domain.Article;
import com.technology.share.domain.view.VArticle;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.ArticleService;
import com.technology.share.service.VArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 文章Controller
 */
@RestController("AdminArticleController")
@RequestMapping("/admin/article")
public class ArticleController extends BaseController<Article,ArticleService> {

    @Autowired
    private VArticleService vArticleService;

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
        QueryWrapper<VArticle> queryWrapper = new QueryWrapper(JSONObject.parseObject(JSONObject.toJSONString(getQueryParam(request)),VArticle.class));
        queryWrapper.orderByDesc("publish_time");
        return ResponseResult.ok(vArticleService.page(new Page<VArticle>(pageNum,pageSize),queryWrapper));
    }
}
