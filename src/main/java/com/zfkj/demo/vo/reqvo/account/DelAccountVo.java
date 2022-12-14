package com.zfkj.demo.vo.reqvo.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelAccountVo {
    @ApiModelProperty(value = "ID",example = "1")
    private Long id;
}
