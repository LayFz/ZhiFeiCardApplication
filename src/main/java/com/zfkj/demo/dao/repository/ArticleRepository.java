package com.zfkj.demo.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfkj.demo.dao.entity.Article;
import com.zfkj.demo.dao.mapper.ArticleMapper;
import org.springframework.stereotype.Repository;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/20 14:27
 */
@Repository
public class ArticleRepository extends ServiceImpl<ArticleMapper, Article> {
}
