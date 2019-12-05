package com.technology.share.controller;

import com.technology.share.domain.FileInfo;
import com.technology.share.enums.CodeMessage;
import com.technology.share.exception.BizException;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController("UploadController")
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping("uploadFile")
    public ResponseResult uploadFile(@RequestParam("file")MultipartFile file)throws Exception{
        if(file == null || file.isEmpty()){
            throw new BizException(CodeMessage.UPLOAD_FILE_EMPTY);
        }
        return ResponseResult.ok(uploadService.upload(file));
    }

    @RequestMapping("uploadCKEditorFile")
    public Map<String,Object> uploadCKEditorFile(@RequestParam("upload")MultipartFile file)throws Exception{
        if(file == null || file.isEmpty()){
            throw new BizException(CodeMessage.UPLOAD_FILE_EMPTY);
        }
        Map<String,Object> map = new LinkedHashMap<>();
        FileInfo fileInfo = uploadService.upload(file);
        map.put("data",fileInfo);
        map.put("uploaded",true);
        map.put("url",fileInfo.getBigUri());
        return map;
    }

}
