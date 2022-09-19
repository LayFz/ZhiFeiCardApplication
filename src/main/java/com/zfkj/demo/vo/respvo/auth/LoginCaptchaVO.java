package com.zfkj.demo.vo.respvo.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 验证码信息VO
 * </p>
 *
 * @author lijunlin
 * @since 2022-1-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginCaptchaVO {
    @ApiModelProperty(value = "验证码ID",example = "1")
    private String captchaId;
    @ApiModelProperty(value = "验证码",example = "1")
    private String captchaCode;
    @ApiModelProperty(value = "图片信息 base64",example = "1")
    private String image;
}
