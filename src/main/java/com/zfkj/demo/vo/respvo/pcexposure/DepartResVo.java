package com.zfkj.demo.vo.respvo.pcexposure;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartResVo {
    private  String departName;
    private Integer departNum;
    private Integer visitNum;
    private String ave_visitNum;
    private Integer customerNum;
    private String ave_customerNum;
    private Integer activeNum;
    private String ave_activeNum;
}
