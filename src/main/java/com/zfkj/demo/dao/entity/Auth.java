package com.zfkj.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zfkj.demo.dao.entity.base.BaseEntity;
import com.zfkj.demo.common.enums.ForSystemEnum;
import com.zfkj.demo.common.enums.MenuTypeEnum;
import com.zfkj.demo.common.enums.RequestMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 菜单资源表
 * </p>
 *
 * @author lijunlin
 * @since 2021-12-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_auth")
public class Auth extends BaseEntity {

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 等级 0为最上级菜单
     */
    private Integer level;

    /**
     * 权限名
     */
    private String name;

    /**
     * URL链接地址
     */
    private String url;

    /**
     * 资源类型：GROUP-菜单、 INNER_LINK-页面、RESOURCE_INNER_LINK-按钮
     */
    private MenuTypeEnum type;

    /**
     * 资源标识
     */
    private String identification;

    /**
     * 资源所属系统 WEB-后台管理系统权限、APP-app权限、MINI-小程序权限
     */
    private ForSystemEnum forSystem;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 请求类型
     */
    private RequestMethodEnum requestMethod;

    /**
     * 图标
     */
    private String icon;

}
