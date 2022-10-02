package com.zfkj.demo.dao.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfkj.demo.dao.entity.ArticleManage;
import com.zfkj.demo.dao.mapper.ArticleManageMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleManageRepository extends ServiceImpl<ArticleManageMapper, ArticleManage> {
}
