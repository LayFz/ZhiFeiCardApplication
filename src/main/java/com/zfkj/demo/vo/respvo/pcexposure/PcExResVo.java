package com.zfkj.demo.vo.respvo.pcexposure;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PcExResVo {
    private  String monthTime;
    private Integer visitNum;
    private Integer saveNum;
    private Integer mailNum;
    private Integer textBoardNum;
    private Integer customerNum;
}
