package com.zfkj.demo.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfkj.demo.dao.entity.ArticleClassif;
import com.zfkj.demo.dao.mapper.ArticleClassifyMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleClassifyRepository extends ServiceImpl<ArticleClassifyMapper, ArticleClassif> {
}
