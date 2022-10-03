package com.zfkj.demo.vo.reqvo.companyIntro;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class delIntroReVo {
    @JsonProperty("id")
    private Long id;
}
