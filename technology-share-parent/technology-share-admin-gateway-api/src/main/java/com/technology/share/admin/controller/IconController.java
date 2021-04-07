package com.technology.share.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.technology.share.domain.Icon;
import com.technology.share.service.IconService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 图标Controller
 */
@RestController("AdminIconController")
@RequestMapping("/admin/icon")
@Api(tags = "图标管理")
public class IconController extends BaseController<Icon,IconService> {

    @Override
    protected QueryWrapper<Icon> getQueryWrapper(HttpServletRequest request) {
        Icon icon = JSONObject.parseObject(JSONObject.toJSONString(getQueryParam(request)),Icon.class);
        QueryWrapper<Icon> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(icon.getIconClass())){
            queryWrapper.like("icon_class",icon.getIconClass());
        }
        return queryWrapper;
    }
}
