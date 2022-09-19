package com.zfkj.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author lijunlin
 * @Description 性别
 * @Date 2022/2/21 16:25
 **/
@Getter
@AllArgsConstructor
public enum SexEnum {
    MAN("MAN","男"),
    WOMAN("WOMAN","女")
    ;
    private final String code;
    private final String name;
}
