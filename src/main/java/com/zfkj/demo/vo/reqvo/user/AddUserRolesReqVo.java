package com.zfkj.demo.vo.reqvo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author: lijunlin
 * @description:
 * @create: 2020-11-23 17:20
 **/
@Data
@Builder
public class AddUserRolesReqVo {

    @ApiModelProperty(value = "用户id",example = "1")
    private Integer userId;

    @ApiModelProperty(value = "角色id集合")
    private List<Integer> roleIds;

}
