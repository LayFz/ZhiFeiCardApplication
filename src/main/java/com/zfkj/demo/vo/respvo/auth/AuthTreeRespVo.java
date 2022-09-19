package com.zfkj.demo.vo.respvo.auth;

import com.zfkj.demo.common.enums.MenuTypeEnum;
import com.zfkj.demo.common.enums.RequestMethodEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijunlin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthTreeRespVo {

    @ApiModelProperty(value = "资源标识,前端校验显示使用",example = "api_v1_user_xxx")
    private String identification;

    @ApiModelProperty(value = "权限名称",example = "首页")
    private String name;

    @ApiModelProperty(value = "ID",example = "1")
    private Long id;

    @ApiModelProperty(value = "GROUP-菜单、INNER_LINK-页面、RESOURCE_INNER_LINK-按钮",example = "GROUP")
    private MenuTypeEnum type;

    @ApiModelProperty(value = "对应接口url",example = "/api/v1/user/xxx")
    private String url;

    @ApiModelProperty(value = "请求方式：POST、GET",example = "POST")
    private RequestMethodEnum requestMethod;

    @ApiModelProperty(value = "子集")
    private List<AuthTreeRespVo> child = new ArrayList<>();

}
