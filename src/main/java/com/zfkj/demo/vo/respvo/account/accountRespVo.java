package com.zfkj.demo.vo.respvo.account;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class accountRespVo {
    private Long id;
    private String name;
    private String phone;
    private String role;
    private LocalDateTime creatime;
    private String adminName;
}
