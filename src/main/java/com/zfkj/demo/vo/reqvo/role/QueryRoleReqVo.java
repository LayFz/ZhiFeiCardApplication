package com.zfkj.demo.vo.reqvo.role;

import com.zfkj.demo.vo.basevo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: lijunlin
 * @description:
 * @create: 2020-11-20 16:59
 **/
@Data
public class QueryRoleReqVo extends PageVO {

    @ApiModelProperty(value = "角色名称",example = "管理员")
    private String roleName;

}
