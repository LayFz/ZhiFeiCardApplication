package com.zfkj.demo.vo.reqvo.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author: lijunlin
 * @description:
 * @create: 2020-11-23 16:04
 **/
@Data
@Builder
public class AddRoleAuthReqVo {

    @ApiModelProperty(value = "角色id",example = "1")
    private Long roleId;

    @ApiModelProperty(value = "权限资源id集合")
    private List<Long> authIds;

}
