package com.technology.share.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.technology.share.domain.FileInfo;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService extends IService<FileInfo> {

    /**
     * 上传文件
     * @param file 传入需要上传的文件
     * @return 返回文件ID
     */
    FileInfo upload(MultipartFile file) throws Exception;
}
