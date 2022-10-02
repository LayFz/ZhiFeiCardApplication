package com.zfkj.demo.vo.respvo.articleClassify;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class articleClassifyRespVo {
        private String name;
        private String adminName;
        private LocalDateTime creatime;
}
