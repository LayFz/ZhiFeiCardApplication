package com.zfkj.demo.vo.reqvo.account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class accountByReVo {
    private Long id;
    private String phone;
    private String name;
}
