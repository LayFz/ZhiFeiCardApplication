package com.zfkj.demo.vo.respvo.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p>
 * 菜单资源VO
 * </p>
 *
 * @author lijunlin
 * @date 2022年1月10日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthVO {
    @ApiModelProperty(value = "菜单ID")
    @JsonIgnore
    private Long id;
    @ApiModelProperty(value = "父级ID")
    @JsonIgnore
    private Long parentId;
    @ApiModelProperty(value = "等级 0为最上级菜单")
    @JsonIgnore
    private Integer level;
    @ApiModelProperty(value = "权限名")
    @JsonIgnore
    private String name;
    @ApiModelProperty(value = "权限名 等同--name")
    private String title;
    @ApiModelProperty(value = "URL链接地址")
    @JsonIgnore
    private String url;
    @ApiModelProperty(value = "按钮地址")
    private String api;
    @ApiModelProperty(value = "URL链接地址 --等同url")
    private String component;
    @ApiModelProperty(value = "是否隐藏菜单项，true 隐藏，false 不隐藏，会注入到路由元数据meta中。")
    @JsonIgnore
    private Boolean invisible;
    @ApiModelProperty(value = "资源类型：GROUP-菜单组、 INNER_LINK-页面、RESOURCE_INNER_LINK-按钮")
    private String type;
    @ApiModelProperty(value = "资源标识")
    @JsonIgnore
    private String identification;
    @ApiModelProperty(value = "资源标识-- 等同 identification")
    private String routerKey;
    @ApiModelProperty(value = "资源所属系统 WEB-后台管理系统权限、APP-app权限、MINI-小程序权限")
    @JsonIgnore
    private String forSystem;
    @ApiModelProperty(value = "排序")
    @JsonIgnore
    private Integer sort;
    @ApiModelProperty(value = "请求类型")
    @JsonIgnore
    private String requestMethod;
    @ApiModelProperty(value = "图标")
    @JsonIgnore
    private String icon;
    @ApiModelProperty(value = "创建人ID")
    @JsonIgnore
    private Long createId;
    @ApiModelProperty(value = "创建时间")
    @JsonIgnore
    private LocalDateTime createTime;
    @ApiModelProperty(value = "修改人ID")
    @JsonIgnore
    private Long updateId;
    @ApiModelProperty(value = "修改时间")
    @JsonIgnore
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "meta信息")
    private AuthMetaVO meta;

}
