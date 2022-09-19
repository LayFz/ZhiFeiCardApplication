package com.zfkj.demo.common.exception;

/**
 * @author: lijunlin
 * @description:
 * @create: 2020-04-06 01:49
 **/
public class BusinessRootRuntimeException extends BusinessSilentException {
    private static final long serialVersionUID = -7382189364622337034L;

    public BusinessRootRuntimeException(String message) {
        super(message);
    }

    public BusinessRootRuntimeException(ExceptionType type, String message) {
        super(type, message);
    }

    public BusinessRootRuntimeException(ExceptionType type) {
        super(type);
    }

    public BusinessRootRuntimeException(ExceptionType type, Object... args) {
        super(type, args);
    }

    public BusinessRootRuntimeException(int exceptionCode, String message) {
        super(exceptionCode, message);
    }

    public BusinessRootRuntimeException(int exceptionCode, String message, Throwable cause) {
        super(exceptionCode, message, cause);
    }

    public BusinessRootRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
