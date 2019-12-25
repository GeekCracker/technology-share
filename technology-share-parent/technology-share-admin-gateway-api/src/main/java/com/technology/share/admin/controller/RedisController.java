package com.technology.share.admin.controller;

import com.technology.share.domain.Redis;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Redis Controller
 */
@RestController("AdminRedisController")
@RequestMapping("/admin/redis")
public class RedisController extends BaseController<Redis, RedisService> {

    @Autowired
    private RedisService redisService;

    /**
     * 查询数据列表（带分页）
     * @param request 传入request请求
     * @return 返回分页数据
     */
    @RequestMapping("queryRedisData")
    public ResponseResult queryRedisData(HttpServletRequest request){
        return ResponseResult.ok(redisService.queryRedisData(getQueryParam(request)));
    }
}
