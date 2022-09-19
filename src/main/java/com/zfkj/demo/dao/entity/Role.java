package com.zfkj.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zfkj.demo.dao.entity.base.BaseEntity;
import com.zfkj.demo.common.enums.YesNoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author lijunlin
 * @since 2021-12-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role")
public class Role extends BaseEntity {


    /**
     * 角色编号
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 是否生效 YES-是，NO-否
     */
    private YesNoEnum isValid;

    /**
     * 角色备注
     */
    private String remark;

}
