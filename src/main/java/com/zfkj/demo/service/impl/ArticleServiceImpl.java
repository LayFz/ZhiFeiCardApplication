package com.zfkj.demo.service.impl;

import cn.hutool.http.HtmlUtil;
import com.zfkj.demo.dao.entity.Article;
import com.zfkj.demo.dao.entity.Role;
import com.zfkj.demo.dao.repository.ArticleRepository;
import com.zfkj.demo.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/20 14:28
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleRepository articleRepository;


    @Override
    public Boolean addOrUpdateArticle(Article reqVo) {
        if (reqVo.getContent()!=null&&reqVo.getName()!=null){
            //防止注入转码存储
            String temp = HtmlUtils.htmlEscapeHex(reqVo.getContent());
            Article article = Article.builder().build();
            //修改对象
            article.setContent(temp);
            BeanUtils.copyProperties(reqVo,article);
            //存储
            articleRepository.saveOrUpdate(article);
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean delArticle(List<Integer> ids) {
        articleRepository.removeByIds(ids);
        return Boolean.TRUE;
    }

    @Override
    public List<Article> selectArticle() {
        return articleRepository.list();
    }
}
