package com.zfkj.demo.common.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * @author: lijunlin
 * @description: 自定义异常信息
 * @create: 2020-04-04 22:56
 **/
public interface ExceptionType {
    int getCode();

    String getDescription();

    default String descriptionWithArgs(Object[] args) {
        return StringUtils.isNotBlank(this.getDescription()) ? String.format(this.getDescription(), args) : this.getDescription();
    }
}
