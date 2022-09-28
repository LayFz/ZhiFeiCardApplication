package com.zfkj.demo.vo.reqvo.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DelAccountVo {
    @ApiModelProperty(value = "ID",example = "1")
    private Long id;
}
