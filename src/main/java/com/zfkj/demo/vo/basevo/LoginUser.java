package com.zfkj.demo.vo.basevo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zfkj.demo.common.enums.OpenCloseEnum;
import com.zfkj.demo.common.enums.SexEnum;
import com.zfkj.demo.vo.respvo.role.RoleRespVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: lijunlin
 * @description: 登录用户信息
 * @create: 2020-06-13 23:30
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {

    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 账号
     */
    private String account;
    /**
     * 电话
     */
    private String phone;
    /**
     * 密码
     */
    private String password;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别：MAN-男,WOMAN-女
     */
    private SexEnum sex;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态：OPEN-启用,CLOSE-禁用
     */
    private OpenCloseEnum status;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 创建人ID
     */
    private Long createId;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 修改人ID
     */
    private Long updateId;
    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 扩展关联属性 begin
     */
    @ApiModelProperty(value = "用户关联的权限集合")
    private List<RoleRespVo> roleList;
}
