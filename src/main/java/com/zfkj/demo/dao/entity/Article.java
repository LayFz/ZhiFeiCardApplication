package com.zfkj.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zfkj.demo.dao.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/20 14:24
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("introarticle")
public class Article extends BaseEntity {
    private String name;
    private String content;
}
