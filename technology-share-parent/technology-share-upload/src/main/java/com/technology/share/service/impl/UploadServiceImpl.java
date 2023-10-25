package com.technology.share.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.technology.share.configuration.upload.UploadConfig;
import com.technology.share.domain.FileInfo;
import com.technology.share.mapper.UploadMapper;
import com.technology.share.service.UploadService;
import com.technology.share.utils.MD5Utils;
import com.technology.share.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.UUID;

@Service
public class UploadServiceImpl extends ServiceImpl<UploadMapper,FileInfo> implements UploadService {

    @Resource
    private UploadMapper uploadMapper;
    @Resource
    private UploadConfig uploadConfig;

    @Override
    public FileInfo upload(MultipartFile file) throws Exception {

        //判断文件是否已存在
        byte [] data = file.getBytes();
        String md5 = MD5Utils.encode(data);
        String hash = MD5Utils.bytesToHexString(data);

        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5",md5).eq("hash",hash);
        FileInfo fileInfo = null;
        synchronized (UploadServiceImpl.class){
            fileInfo = uploadMapper.selectOne(queryWrapper);
        }
        //通过md5与hash值查询到了文件，说明文件已经存在，直接返回文件ID
        if(fileInfo != null){
            return fileInfo;
        }
        //组装添加到数据库的文件信息
        fileInfo = new FileInfo();
        fileInfo.setMd5(md5);
        fileInfo.setHash(hash);
        fileInfo.setSize(file.getSize());
        fileInfo.setUseCount(0);
        fileInfo.setUuid(UUID.randomUUID().toString());
        String originalFilename = file.getOriginalFilename();
        fileInfo.setOriginalName(originalFilename);
        fileInfo.setExt(originalFilename.substring(originalFilename.lastIndexOf(".")+1));


        //组装URI
        StringBuilder sb = new StringBuilder();
        sb.append(uploadConfig.getPrefix())
                .append("/").append(fileInfo.getExt())
                .append("/").append(UploadUtils.scatterByHour())
                .append("/");
        String baseURI = sb.toString();
        String bigURI = baseURI+fileInfo.getUuid()+"."+fileInfo.getExt();
        String smallURI = baseURI+"small-"+fileInfo.getUuid()+"."+fileInfo.getExt();
        fileInfo.setBigUri(bigURI);
        fileInfo.setSmallUri(smallURI);

        //保存文件信息到数据库
        uploadMapper.insert(fileInfo);

        //保存文件
        File bigFile = new File(uploadConfig.getSavePath()+bigURI);
        File parentFile = bigFile.getParentFile();
        if(!parentFile.exists()){
            if(!parentFile.mkdirs()){
                throw new RuntimeException("父目录创建失败");
            }
        }

        FileCopyUtils.copy(data,bigFile);
        //生成缩略图
        File smallFile = new File(uploadConfig.getSavePath()+smallURI);
        UploadUtils.toSmallImg(bigFile,smallFile);
        return fileInfo;
    }
}
