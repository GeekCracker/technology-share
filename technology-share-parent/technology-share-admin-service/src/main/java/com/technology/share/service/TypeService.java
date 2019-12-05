package com.technology.share.service;

import com.technology.share.domain.Type;

import java.util.List;

public interface TypeService extends BaseService<Type> {

    /**
     * 获取类型树
     * @return 返回类型树
     */
    List<Type> typeTree();
}
