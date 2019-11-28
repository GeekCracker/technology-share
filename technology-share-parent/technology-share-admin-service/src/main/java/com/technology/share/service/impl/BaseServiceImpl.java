package com.technology.share.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.technology.share.service.BaseService;

public class BaseServiceImpl<M extends BaseMapper<T>,T> extends ServiceImpl<M,T> implements BaseService<T> {
}
