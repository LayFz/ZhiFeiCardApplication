package com.zfkj.demo.service;

import com.zfkj.demo.vo.reqvo.article.AddUpArticleVo;
import com.zfkj.demo.vo.reqvo.article.DelArticleVo;
import com.zfkj.demo.vo.reqvo.article.ArticleReqVo;
import com.zfkj.demo.vo.respvo.article.articleByVo;

import java.util.List;

public interface ArticleManageService {
    /**
     * 个性化内容--文章管理
     */


    /**
     * 获取文章数据
     */
    List<ArticleReqVo> getArticleData();

    /**
     * 查询文章
     */
    List<ArticleReqVo> selectArticle(articleByVo ByVo);

    /**
     * 添加文章
     */
    Boolean addArticle(AddUpArticleVo reVo);

    /**
     * 编辑文章
     */
    Boolean updataArticle(AddUpArticleVo reVo);

    /**
     * 删除文章
     */
    Boolean DelArticle(DelArticleVo reVo);
}
