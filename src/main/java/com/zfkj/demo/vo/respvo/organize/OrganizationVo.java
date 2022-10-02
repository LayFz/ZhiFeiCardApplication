package com.zfkj.demo.vo.respvo.organize;

import com.zfkj.demo.dao.entity.Organize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @ApiModelProperty(value = "Name",example = "星之援科技有限公司")
    private String company_name;
    @ApiModelProperty(value = "ID",example = "1")
    private Long id;
    @ApiModelProperty(value = "NAME",example = "人力部")
    private String main_name;
    private List<Organize> organizes;
}
