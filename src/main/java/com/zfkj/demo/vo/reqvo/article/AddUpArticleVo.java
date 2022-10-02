package com.zfkj.demo.vo.reqvo.article;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddUpArticleVo {
    private Long id;
    private String name;
    private String classIf;
    private int falseNumber;
    private String headImg;
    private String content;

}
