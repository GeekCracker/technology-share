package com.technology.share.admin.controller;

import com.technology.share.domain.FileInfo;
import com.technology.share.service.FileInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("AdminFileInfoController")
@RequestMapping("/admin/fileInfo")
public class FileInfoController extends BaseController<FileInfo, FileInfoService> {
}
