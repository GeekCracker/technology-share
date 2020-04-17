package com.technology.share.admin.controller;

import com.technology.share.domain.FileInfo;
import com.technology.share.service.FileInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("AdminFileInfoController")
@RequestMapping("/admin/fileInfo")
@Api(tags = "文件上传记录管理")
public class FileInfoController extends BaseController<FileInfo, FileInfoService> {
}
