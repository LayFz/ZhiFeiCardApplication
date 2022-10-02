package com.zfkj.demo.vo.reqvo.account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountByReVo {
    private Long id;
    private String phone;
    private String name;
}
