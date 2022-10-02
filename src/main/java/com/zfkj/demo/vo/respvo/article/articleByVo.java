package com.zfkj.demo.vo.respvo.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class articleByVo {
    private  Long id;
    private String name;
    private String classif;
}
