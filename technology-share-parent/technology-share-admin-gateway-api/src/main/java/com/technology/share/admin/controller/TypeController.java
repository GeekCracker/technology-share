package com.technology.share.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.technology.share.domain.Type;
import com.technology.share.domain.view.VType;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.TypeService;
import com.technology.share.service.VTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 类别Controller
 */
@RestController("AdminTypeController")
@RequestMapping("/admin/type")
public class TypeController extends BaseController<Type, TypeService> {

    @Autowired
    private VTypeService vTypeService;

    @Override
    public ResponseResult queryPageData(HttpServletRequest request) {
        QueryWrapper<VType> queryWrapper = getQueryWrapper(request,VType.class);
        queryWrapper.orderByDesc("create_time");
        return ResponseResult.ok(vTypeService.page(new Page<>(getPageNum(request),getPageSize(request)),queryWrapper));
    }

    @RequestMapping("typeTree")
    public ResponseResult typeTree(){
        return ResponseResult.ok(service.typeTree());
    }
}
