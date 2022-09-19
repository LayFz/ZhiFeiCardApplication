package com.zfkj.demo.vo.respvo.user;

import com.zfkj.demo.common.enums.OpenCloseEnum;
import com.zfkj.demo.common.enums.SexEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author: lijunlin
 * @description: 查询用户RespVo
 * @create: 2019-12-27 15:03
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryUserRespVo  {
    @ApiModelProperty(value = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;
    @ApiModelProperty(value = "账号", example = "admin")
    private String account;
    @ApiModelProperty(value = "电话", example = "13555555555")
    private String phone;
    @ApiModelProperty(value = "年龄", example = "18")
    private Integer age;
    @ApiModelProperty(value = "性别：MAN-男,WOMAN-女", example = "MAN")
    private SexEnum sex;
    @ApiModelProperty(value = "详细地址", example = "xxxxx")
    private String address;
    @ApiModelProperty(value = "备注", example = "这个人很懒,什么都没留下")
    private String remark;
    @ApiModelProperty(value = "用户角色", example = "管理员")
    private String roleName;
    @ApiModelProperty(value = "状态：OPEN-启用,CLOSE-禁用", example = "OPEN")
    private OpenCloseEnum status;

}
