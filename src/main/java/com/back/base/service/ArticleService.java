package com.back.base.service;

import com.back.base.model.Article;

import java.util.List;

public interface ArticleService {

    public Article find(String id);

    public Article save(Article role);

    public Article update(Article role);

    public List<Article> list(Article role);

    public Article saveOrUpdate(Article role);

    public int delete(String id);

}
