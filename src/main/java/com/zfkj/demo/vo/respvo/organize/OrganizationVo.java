package com.zfkj.demo.vo.respvo.organize;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/26 18:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationVo {
    @ApiModelProperty(value = "ID",example = "1")
    private Long id;
}
