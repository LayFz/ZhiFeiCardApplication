package com.zfkj.demo.vo.reqvo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 登录信息体
 * </p>
 *
 * @author lijunlin
 * @date 2022-1-7
 */
@Data
public class LoginUserInfoVO {

    @ApiModelProperty(value = "验证码Id", example = "uuid", required = true)
    private String captchId;

    @ApiModelProperty(value = "验证码", example = "1234", required = true)
    private String captchCode;

    @ApiModelProperty(value = "平台账号（手机号或用户账号）", example = "admin")
    private String userAccount;

    @ApiModelProperty(value = "密码", example = "888888", required = true)
    private String password;

}
