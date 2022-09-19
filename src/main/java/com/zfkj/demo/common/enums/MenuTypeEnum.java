package com.zfkj.demo.common.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型
 * @author lijunlin
 * @date 2022年1月12日
 **/
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {
    GROUP("GROUP","菜单组",0),
    INNER_LINK("INNER_LINK","菜单",1),
    RESOURCE_INNER_LINK("RESOURCE_INNER_LINK","按钮",2),
    ;
    // 类型
    private final String code;
    // 名称
    private final String name;
    // 等级
    private final Integer level;

    public static MenuTypeEnum getInfoByType(String type){
        for (MenuTypeEnum val : MenuTypeEnum.values()) {
            if (StrUtil.equalsIgnoreCase(type,val.getCode())) {
                return val;
            }
        }
        return INNER_LINK;
    }
}
