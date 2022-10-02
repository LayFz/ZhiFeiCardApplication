package com.zfkj.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zfkj.demo.dao.entity.base.BaseEntity;
import com.zfkj.demo.common.enums.OpenCloseEnum;
import com.zfkj.demo.common.enums.SexEnum;
import com.zfkj.demo.common.enums.YesNoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author lijunlin
 * @since 2021-12-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class User extends BaseEntity {

    /**
     * 姓名
     */
    private String name;

    /**
     * 系统账号
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
     * 详细地址
     */
    private String address;

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
     * 是否删除：YES-是,NO-否
     */
    private YesNoEnum delFlag;

    /**
     * 最后一次登录时间
     */
    private Date lastLogin;

}
