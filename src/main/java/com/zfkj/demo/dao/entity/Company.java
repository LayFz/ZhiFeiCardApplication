package com.zfkj.demo.dao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zfkj.demo.common.enums.OpenCloseEnum;
import com.zfkj.demo.dao.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("company")
public class Company extends BaseEntity {

    /**
     *企业编号
     */
    @TableField("company_number")
    private String number;

    /**
     * 企业名称
     */
    @TableField("company_name")
    private String name;

    /**
     * 企业简称
     */
    @TableField("company_nickname")
    private  String nickname;

    /**
     * logo路径
     */
    private String logoImg;
    /**
     * 管理员姓名
     */
    private String adminName;

    /**
     * 管理员电话
     */
    @TableField("admin_phone")
    private String phone;

    /**
     * 用户id
     */
    private int userId;

    /**
     * 企业有效期
     */
    @TableField("valid_data")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date validData;

    /**
     * 是否启用：OPEN-启用，CLOSE-禁用
     */
    @TableField("is_valid")
    private OpenCloseEnum isVaild;

}
