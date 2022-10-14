package com.zfkj.demo.service;

import com.zfkj.demo.dao.entity.Article;
import com.zfkj.demo.dao.entity.ArticleManage;

import java.util.List;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/20 14:28
 */
public interface ArticleService {
    /**
     * 添加文章接口
     * @return
     */
    Boolean addOrUpdateArticle(Article reqVo);

    /**
     * 删除文章
     * @param List<Integer> ids
     * @return
     */
    Boolean delArticle(List<Integer> ids);

    /**
     * 查看文章
     * @return
     */
    List<Article> selectArticle();

    /**
     * 根据分类查询文章列表
     * @param classfy_id
     * @return
     */

    List<ArticleManage> getArticleListById(Integer classfy_id);

    /**
     * 根据文章id查询文章
     * @param article_id
     * @return
     */
    ArticleManage getArticleById(Integer article_id);
}
