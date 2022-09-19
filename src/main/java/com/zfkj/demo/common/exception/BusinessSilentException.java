package com.zfkj.demo.common.exception;


import org.apache.commons.lang3.ArrayUtils;

/**
 * @author: lijunlin
 * @description:
 * @create: 2020-04-06 01:49
 **/
public class BusinessSilentException extends RuntimeException implements BusinessException {
    private static final long serialVersionUID = -6403033409061476727L;
    private Integer code;
    private String description;

    public BusinessSilentException(String message) {
        super(message);
        this.code = 500;
        this.description = message;
    }

    public BusinessSilentException(ExceptionType type, String message) {
        super(message);
        this.code = 500;
        this.description = message;
        this.code = type.getCode();
    }

    public BusinessSilentException(ExceptionType type) {
        this(type, type.getDescription());
    }

    public BusinessSilentException(ExceptionType type, Object... args) {
        this(type, ArrayUtils.isNotEmpty(args) ? String.format(type.getDescription(), args) : type.getDescription());
    }

    public BusinessSilentException(int exceptionCode, String message) {
        super(message);
        this.code = 500;
        this.code = exceptionCode;
        this.description = message;
    }

    public BusinessSilentException(int exceptionCode, String message, Throwable cause) {
        super(message, cause);
        this.code = 500;
        this.code = exceptionCode;
        this.description = message;
    }

    public BusinessSilentException(String message, Throwable cause) {
        super(message, cause);
        this.code = 500;
        this.description = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }
}

