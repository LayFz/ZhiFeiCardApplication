package com.zfkj.demo.vo.reqvo.pcexposure;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DePartReVo {
    private String startTime;
    private String endTime;
    @ApiModelProperty(value = "departLevel:", example ="1")
    private Integer departLevel;
    @ApiModelProperty(value = "rank",example = "1")
    private Integer rank;
}
