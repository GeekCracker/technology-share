package com.technology.share.service.impl;

import com.technology.share.domain.FileInfo;
import com.technology.share.mapper.FileInfoMapper;
import com.technology.share.service.FileInfoService;
import org.springframework.stereotype.Service;

@Service("AdminFileInfoService")
public class FileInfoServiceImpl extends BaseServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {
}
