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
public enum OpenCloseEnum {
    OPEN("OPEN","开启"),
    CLOSE("CLOSE","禁用")
    ;
    private final String code;
    private final String name;
}
