package com.zfkj.demo.vo.respvo.role;

import com.google.common.collect.Lists;
import com.zfkj.demo.common.enums.ForSystemEnum;
import com.zfkj.demo.common.enums.MenuTypeEnum;
import com.zfkj.demo.common.enums.RequestMethodEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author: lijunlin
 * @description:
 * @create: 2020-11-23 14:53
 **/
@Data
@Builder
public class RoleAuthListRespVo {
    @ApiModelProperty(value = "ID",example = "1")
    private Long id;

    @ApiModelProperty(value = "父级ID",example = "1")
    private Long parentId;

    @ApiModelProperty(value = "权限层级,0为最顶级菜单",example = "0")
    private Integer level;

    @ApiModelProperty(value = "权限名称",example = "首页")
    private String name;

    @ApiModelProperty(value = "对应接口url",example = "/api/v1/user/xxx")
    private String url;

    @ApiModelProperty(value = "GROUP-菜单、INNER_LINK-页面、RESOURCE_INNER_LINK-按钮",example = "GROUP")
    private MenuTypeEnum type;

    @ApiModelProperty(value = "资源标识,前端校验显示使用",example = "api_v1_user_xxx")
    private String identification;

    @ApiModelProperty(value = "图标",example = "xxxx")
    private String icon;

    @ApiModelProperty(value = "排序",example = "1")
    private Integer sort;

    @ApiModelProperty(value = "资源所属系统 WEB-后台管理系统权限、APP-app权限、MINI-小程序权限",example = "WEB")
    private ForSystemEnum forSystem;

    @ApiModelProperty(value = "请求方式：POST、GET",example = "POST")
    private RequestMethodEnum requestMethod;

    @ApiModelProperty(value = "是否选中：YES-是、NO-否",example = "YES")
    private Boolean isSelect;

    @ApiModelProperty(value = "子集")
    private List<RoleAuthListRespVo> childs = Lists.newArrayList();
}
