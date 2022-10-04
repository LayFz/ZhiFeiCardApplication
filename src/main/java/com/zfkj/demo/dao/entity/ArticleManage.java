package com.zfkj.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zfkj.demo.dao.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("article")
public class ArticleManage extends BaseEntity {
    private Long id;
    private String name;
    private Long classifyId;
    private int viewNumber;
    private int falseNumber;
    private String content;
    private String headImg;


}
