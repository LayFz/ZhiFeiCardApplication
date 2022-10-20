package com.zfkj.demo.vo.reqvo.customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class pcCusReVo {
    private Integer id;
    private String name;
    private Integer staffId;
    private String staffName;
}
