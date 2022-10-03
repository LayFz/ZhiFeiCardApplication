package com.zfkj.demo.vo.reqvo.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUpAccountVo {
    @ApiModelProperty(value = "姓名",example = "张三")
    private String name;
    @ApiModelProperty(value = "手机号",example = "12345678980")
    private String phone;
    @ApiModelProperty(value = "角色",example = "管理员")
    private int roleId;
}
