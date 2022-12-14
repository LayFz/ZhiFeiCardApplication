package com.zfkj.demo.vo.reqvo.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RePassAccountVo {
    @ApiModelProperty(value = "id",example = "1")
    private Long id;
    @ApiModelProperty(value = "电话号码",example = "1")
    private String phone;
}
