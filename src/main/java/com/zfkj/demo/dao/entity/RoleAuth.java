package com.zfkj.demo.dao.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.zfkj.demo.dao.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

/**
 * <p>
 * 角色资源关联表
 * </p>
 *
 * @author lijunlin
 * @since 2021-12-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role_auth")
public class RoleAuth extends BaseEntity {

    /**
     * 角色ID（引用role表id）
     */
    private Long roleId;

    /**
     * 菜单ID （引用auth表id）
     */
    private Long authId;

}
