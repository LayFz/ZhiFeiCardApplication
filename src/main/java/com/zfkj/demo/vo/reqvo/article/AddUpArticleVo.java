package com.zfkj.demo.vo.reqvo.article;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddUpArticleVo {
    //文章id
    private int id;
    //分类id
    private Long class_id;
    //标题
    private String name;
    //基础浏览量
    private int falseNumber;
    //标题图片
    private String headImg;
    //正文
    private String content;

}
