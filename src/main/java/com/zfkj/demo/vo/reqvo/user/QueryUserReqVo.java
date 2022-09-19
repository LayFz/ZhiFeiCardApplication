package com.zfkj.demo.vo.reqvo.user;

import com.zfkj.demo.vo.basevo.PageVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: lijunlin
 * @description: 查询用户ReqVo
 * @create: 2019-12-27 15:03
 **/
@Data
public class QueryUserReqVo extends PageVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;
    @ApiModelProperty(value = "账号", example = "admin")
    private String account;
    @ApiModelProperty(value = "电话", example = "13555555555")
    private String phone;
    @ApiModelProperty(value = "详细地址", example = "xxxxx")
    private String address;
    @ApiModelProperty(value = "备注", example = "这个人很懒,什么都没留下")
    private String remark;

}
