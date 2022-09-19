package com.zfkj.demo.vo.reqvo.user;

import com.zfkj.demo.common.enums.OpenCloseEnum;
import com.zfkj.demo.common.enums.SexEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: lijunlin
 * @description: 用户保存或修改ReqVo
 * @create: 2019-12-27 15:03
 **/
@Data
public class UserSaveUpdateReqVo {

    @ApiModelProperty(value = "id(保存时不传,修改传)", example = "1")
    private Long id;
    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;
    @ApiModelProperty(value = "账号", example = "admin")
    private String account;
    @ApiModelProperty(value = "电话", example = "13555555555")
    @Length(min = 11,max = 11,message = "电话号码的长度必须是11位")
    private String phone;
    @ApiModelProperty(value = "密码(保存时传,修改不传)", example = "123456")
    private String password;
    @ApiModelProperty(value = "年龄", example = "18")
    private Integer age;
    @ApiModelProperty(value = "性别：MAN-男,WOMAN-女", example = "MAN")
    private SexEnum sex;
    @ApiModelProperty(value = "详细地址", example = "xxxxx")
    private String address;
    @ApiModelProperty(value = "备注", example = "这个人很懒,什么都没留下")
    private String remark;
    @ApiModelProperty(value = "状态：OPEN-启用,CLOSE-禁用", example = "OPEN")
    private OpenCloseEnum status;

}
