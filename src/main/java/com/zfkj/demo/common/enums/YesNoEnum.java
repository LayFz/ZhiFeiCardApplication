package com.zfkj.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: lijunlin
 * @description:
 * @create: 2020-06-14 09:14
 **/
@Getter
@AllArgsConstructor
public enum YesNoEnum {
    YES("是","YES"),
    NO("否","NO")
    ;
    private final String name;
    private final String code;
}
