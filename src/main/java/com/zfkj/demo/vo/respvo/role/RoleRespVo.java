package com.zfkj.demo.vo.respvo.role;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zfkj.demo.common.enums.YesNoEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author: lijunlin
 * @description:
 * @create: 2020-10-22 16:14
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleRespVo {

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

    @ApiModelProperty(value = "创建人id",example = "1")
    private Long createId;

    @ApiModelProperty(value = "创建时间",example = "2019-01-01 12:12:12")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人id",example = "1")
    private Long updateId;

    @ApiModelProperty(value = "修改时间",example = "2019-01-01 12:12:12")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
