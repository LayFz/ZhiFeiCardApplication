package com.zfkj.demo.vo.respvo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijunlin
 */
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class AuthTreeTopRespVo {

    private String name = "顶级";

    private List<AuthTreeRespVo> child = new ArrayList<>();
}
