package com.technology.share.admin.controller;

import com.technology.share.domain.Sql;
import com.technology.share.service.SqlService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: SQLController
 * @author: 朱俊亮
 * @time: 2020/4/7 19:00
 */
@RestController("AdminSqlController")
@RequestMapping("/admin/sql")
@Api(tags = "SQL语句管理")
public class SqlController extends BaseController<Sql, SqlService> {
}
