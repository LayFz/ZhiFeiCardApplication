package com.zfkj.demo.vo.reqvo.role;

import com.zfkj.demo.common.enums.YesNoEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author: lijunlin
 * @description:
 * @create: 2020-11-23 09:36
 **/
@Data
@Builder
public class AddUpdateRoleReqVo {
    @ApiModelProperty(value = "id",example = "1")
    private Long id;

    @ApiModelProperty(value = "角色编码",example = "JS001")
    private String roleCode;

    @ApiModelProperty(value = "角色名称",example = "超级管理员")
    private String roleName;

    @ApiModelProperty(value = "是否有效 YES-是、NO-否",example = "YES")
    private YesNoEnum isValid;

    @ApiModelProperty(value = "角色说明",example = "角色说明")
    private String remark;
}
