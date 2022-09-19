package com.zfkj.demo.vo.respvo.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zfkj.demo.common.enums.YesNoEnum;
import com.zfkj.demo.vo.respvo.auth.AuthVO;
import com.zfkj.demo.vo.respvo.role.RoleRespVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户信息VO
 * </p>
 *
 * @author lijunlin
 * @since 2022-1-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO {

    @ApiModelProperty(value = "用户角色信息")
    private List<RoleRespVo> roles;

    @ApiModelProperty(value = "API权限")
    @JsonIgnore
    private List<AuthVO> apiAuth;

    @ApiModelProperty(value = "用户ID",example = "1")
    private Long id;

    @ApiModelProperty(value = "姓名",example = "name")
    private String name;
    @ApiModelProperty(value = "系统账号",example = "account")
    private String account;
    @ApiModelProperty(value = "电话",example = "18888888888")
    private String phone;
    @ApiModelProperty(value = "年龄",example = "1")
    private Integer age;
    @ApiModelProperty(value = "性别：MAN-男,WOMAN-女",example = "MAN")
    private String sex;
    @ApiModelProperty(value = "备注",example = "remark")
    private String remark;
    @ApiModelProperty(value = "状态：OPEN-启用,CLOSE-禁用",example = "OPEN")
    private String status;
    @ApiModelProperty(value = "创建人ID",example = "1")
    private Long createId;
    @ApiModelProperty(value = "创建时间",example = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty(value = "修改人ID",example = "1")
    private Long updateId;
    @ApiModelProperty(value = "修改时间",example = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "是否超级管理员：YES-是,NO-否",example = "YES")
    private YesNoEnum superAdmin;

    @ApiModelProperty(value = "交互信息token",example = "1")
    private String token;
}
