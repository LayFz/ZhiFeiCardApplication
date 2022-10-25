package com.zfkj.demo.vo.respvo.miniexposure;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MiniExResVo {
  private  String dayTime;
  private Integer visitNum;
  private String aveNum;
  private Integer saveNum;
  private Integer mailNum;
  private Integer textBoardNum;
}
