package com.zfkj.demo.vo.reqvo.card;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveCardIntroReVo {
    private Integer id;
    private String imgUrl;
    private String content;
}
