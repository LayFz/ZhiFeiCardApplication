package com.zfkj.demo.vo.respvo.companyIntro;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class introRespVo {
    private Integer id;
    private String name;
    private String content;
    private LocalDateTime creatime;
    private String adminName;
}
