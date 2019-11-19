package com.back.base.service.impl;

import com.back.base.dao.ArticleColumnsMapper;
import com.back.base.model.Article;
import com.back.base.model.ArticleColumns;
import com.back.base.service.ArticleColumnsService;
import com.back.base.service.ArticleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleColumnsServiceImpl implements ArticleColumnsService {

    private static final Logger logger = Logger.getLogger(ArticleColumnsServiceImpl.class);

    @Autowired(required = true)
    private ArticleColumnsMapper articleColumnsMapper;

    public ArticleColumns find(String id) {
        return articleColumnsMapper.selectByPrimaryKey(id);
    }

    public ArticleColumns save(ArticleColumns articleColumns) {
        articleColumnsMapper.insert(articleColumns);
        logger.info(ArticleColumns.class.getName() + "数据插入成功！");
        return articleColumns;
    }

    public ArticleColumns update(ArticleColumns articleColumns) {
        articleColumnsMapper.updateByPrimaryKeySelective(articleColumns);
        logger.info(ArticleColumns.class.getName() + "数据更新成功！");
        return articleColumns;
    }

    public List<ArticleColumns> list(ArticleColumns articleColumns) {
        return articleColumnsMapper.select(articleColumns);
    }

    public ArticleColumns saveOrUpdate(ArticleColumns articleColumns) {
        if (articleColumns.isUpdateFlag()) {
            update(articleColumns);
        } else {
            save(articleColumns);
        }
        return articleColumns;
    }

    public int delete(String id) {
        int count = articleColumnsMapper.deleteByPrimaryKey(id);
        logger.info(ArticleColumns.class.getName() + "数据删除成功！");
        return count;
    }
}
