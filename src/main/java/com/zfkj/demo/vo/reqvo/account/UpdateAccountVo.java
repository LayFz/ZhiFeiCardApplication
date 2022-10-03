package com.zfkj.demo.vo.reqvo.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/10/2 18:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountVo {
    @ApiModelProperty(value = "id",example = "2")
    private int id;
    @ApiModelProperty(value = "姓名",example = "张三")
    private String name;
    @ApiModelProperty(value = "手机号",example = "12345678980")
    private String phone;
    @ApiModelProperty(value = "角色",example = "2")
    private int roleId;
}
