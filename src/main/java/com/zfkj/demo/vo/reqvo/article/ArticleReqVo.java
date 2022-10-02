package com.zfkj.demo.vo.reqvo.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleReqVo {
    private Long id;
    private String name;
    private String classif;
    private  int falsenumber;
    private int trueNumber;
    private LocalDateTime createtime;
    private String createName;


}
