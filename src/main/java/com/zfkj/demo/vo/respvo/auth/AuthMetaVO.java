package com.zfkj.demo.vo.respvo.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 个人菜单meta信息
 */
@Data
public class AuthMetaVO {
    @ApiModelProperty("菜单key")
    private String permissionKey;
    @ApiModelProperty("菜单对应按钮")
    private List<String> permissionBtn;
    //    private String authority;
    @ApiModelProperty("图标")
    private String icon;
    @ApiModelProperty(value = "是否隐藏菜单项，1 true 隐藏，0 false 不隐藏，会注入到路由元数据meta中。")
    private Boolean invisible;
}
