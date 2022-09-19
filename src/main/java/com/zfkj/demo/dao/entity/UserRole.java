package com.zfkj.demo.dao.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.zfkj.demo.dao.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用户角色关联
 * </p>
 *
 * @author lijunlin
 * @since 2021-12-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user_role")
public class UserRole extends BaseEntity {


    /**
     * 用户Id（引用sys_user表id）
     */
    private Integer userId;

    /**
     * 角色Id（引用role表id）
     */
    private Integer roleId;

}
