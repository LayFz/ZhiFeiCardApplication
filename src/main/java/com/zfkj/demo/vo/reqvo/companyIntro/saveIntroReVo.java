package com.zfkj.demo.vo.reqvo.companyIntro;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class saveIntroReVo {
    private Long id;
    private String name;
    private String content;
}
