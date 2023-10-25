package com.technology.share.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.technology.share.domain.Article;
import com.technology.share.domain.view.VArticle;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.ArticleService;
import com.technology.share.service.VArticleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 文章Controller
 */
@RestController("AdminArticleController")
@RequestMapping("/admin/article")
@Api(tags = "文章管理")
public class ArticleController extends BaseController<Article,ArticleService> {

    @Autowired
    private VArticleService vArticleService;

    @Override
    public ResponseResult queryPageData(HttpServletRequest request) {
        JSONObject queryParam = getQueryParam(request);
        QueryWrapper<VArticle> queryWrapper = getQueryWrapper(queryParam, VArticle.class);
        queryWrapper.orderByDesc("publish_time");
        return ResponseResult.ok(vArticleService.page(new Page<>(getPageNum(queryParam),getPageSize(queryParam)), queryWrapper));
    }
}
