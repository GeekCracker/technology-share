package com.technology.share.service.impl;

import com.technology.share.domain.view.VArticle;
import com.technology.share.mapper.VArticleMapper;
import com.technology.share.service.VArticleService;
import org.springframework.stereotype.Service;

@Service("AdminVArticleService")
public class VArticleServiceImpl extends BaseServiceImpl<VArticleMapper, VArticle> implements VArticleService
{
}
