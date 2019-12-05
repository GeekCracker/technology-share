package com.technology.share.service.impl;

import com.technology.share.domain.Type;
import com.technology.share.mapper.TypeMapper;
import com.technology.share.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service("AdminTypeService")
public class TypeServiceImpl extends BaseServiceImpl<TypeMapper, Type> implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> typeTree() {
        List<Type> all = list();
        if(all == null || all.isEmpty()){
            all = Collections.EMPTY_LIST;
            return all;
        }
        List top = getTopType(all);
        return deep(all,top);
    }

    /**
     * 获取顶级的类别
     * @param all 传入所有的类别
     * @return 返回顶级的类别
     */
    private List<Type> getTopType(List<Type> all){
        return all.stream().filter(item->item.getParentIdRaw() == null || item.getParentIdRaw() == 0).collect(Collectors.toList());
    }

    /**
     * 递归组装类型树
     * @param all 传入所有的类型
     * @param typeTree 类型树
     * @return 返回类型树
     */
    private List<Type> deep(List<Type> all,List<Type> typeTree){
        if(typeTree != null && !typeTree.isEmpty()){
            for(Type type : typeTree){
                List<Type> children = all.stream().filter(item->type.getIdRaw().equals(item.getParentIdRaw())).collect(Collectors.toList());
                if(!children.isEmpty()){
                    deep(all,children);
                }
                type.setChildren(children);
            }
        }
        return typeTree;
    }

}
