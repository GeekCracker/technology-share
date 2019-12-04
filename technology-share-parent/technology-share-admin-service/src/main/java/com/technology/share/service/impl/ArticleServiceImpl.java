package com.technology.share.service.impl;

import com.technology.share.domain.Article;
import com.technology.share.mapper.ArticleMapper;
import com.technology.share.service.ArticleService;
import org.springframework.stereotype.Service;

@Service("AdminArticleService")
public class ArticleServiceImpl extends BaseServiceImpl<ArticleMapper, Article> implements ArticleService {
}
