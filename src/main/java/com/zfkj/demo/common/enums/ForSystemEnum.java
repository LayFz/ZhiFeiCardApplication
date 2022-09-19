package com.zfkj.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单平台类型
 * @author lijunlin
 * @date 2022年1月12日
 **/
@Getter
@AllArgsConstructor
public enum ForSystemEnum {
    // 资源所属系统 WEB-后台管理系统权限、APP-app权限、MINI-小程序权限
    WEB("WEB","后台管理系统权限"),
    MINI("MINI","小程序权限"),
    APP("APP","app权限")
    ;
    private final String code;
    private final String name;
}
