package com.zfkj.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zfkj.demo.common.enums.OpenCloseEnum;
import com.zfkj.demo.dao.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("company")
public class Company extends BaseEntity {

    /**
     *企业编号
     */
    private String number;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 企业简称
     */
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
    private String phone;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 企业有效期
     */
    private LocalDateTime vaildData;

    /**
     * 是否启用：OPEN-启用，CLOSE-禁用
     */
    private OpenCloseEnum isVaild;

}
