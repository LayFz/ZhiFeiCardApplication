package com.zfkj.demo.vo.respvo.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 菜单组件信息
 *
 * @author lijunlin
 * @date 2022年1月12日
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuCompentVO {
    @ApiModelProperty("路由")
    private String router;
    @ApiModelProperty("子组件")
    private List<String> children;
    @ApiModelProperty("图标")
    private String icon;
    @ApiModelProperty("路由名称")
    private String name;
    @ApiModelProperty("待定")
    private String authority;
    @ApiModelProperty("路由为超链接时  此值为 超链接地址")
    private String link;
    @ApiModelProperty("path")
    private String path;

}
