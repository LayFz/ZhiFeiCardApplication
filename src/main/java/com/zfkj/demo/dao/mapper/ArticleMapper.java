package com.zfkj.demo.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zfkj.demo.dao.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/20 14:26
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
