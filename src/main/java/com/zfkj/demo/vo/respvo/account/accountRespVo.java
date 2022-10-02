package com.zfkj.demo.vo.respvo.account;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class accountRespVo {
    private Long id;
    private String name;
    private String phone;
    private String role;
    private LocalDateTime creatime;
    private String adminName;
}
