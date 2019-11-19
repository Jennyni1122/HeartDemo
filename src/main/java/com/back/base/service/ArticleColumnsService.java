package com.back.base.service;

import com.back.base.model.ArticleColumns;

import java.util.List;

public interface ArticleColumnsService {

    public ArticleColumns find(String id);

    public ArticleColumns save(ArticleColumns role);

    public ArticleColumns update(ArticleColumns role);

    public List<ArticleColumns> list(ArticleColumns role);

    public ArticleColumns saveOrUpdate(ArticleColumns role);

    public int delete(String id);

}
