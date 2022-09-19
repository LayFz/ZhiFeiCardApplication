package com.zfkj.demo.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: lijunlin
 * @description: 全局controller异常处理类
 * @create: 2020-01-08 11:46
 **/
@RestControllerAdvice
@Slf4j
public class BusinessExceptionAdvice {


    public static final String ERROR = "响应业务异常";

    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception ex, HttpServletResponse response, HttpServletRequest request) throws Exception {
        int errorCode = 500;
        String errorMsg;
        if (ex instanceof BusinessException) {
            //AssertUtils断言抛出的自定义业务异常
            errorCode = ((BusinessException) ex).getCode();
            errorMsg = ((BusinessException) ex).getDescription();
            if (log.isDebugEnabled()) {
                log.debug(ERROR, ex);
            }
        } else if(ex instanceof BusinessRootRuntimeException){
            //AssertUtils断言抛出的自定义业务异常
            errorCode = ((BusinessRootRuntimeException) ex).getCode();
            errorMsg = ((BusinessRootRuntimeException) ex).getDescription();
            if (log.isDebugEnabled()) {
                log.debug(ERROR, ex);
            }
        } else if (ex instanceof MethodArgumentNotValidException){
            //reqvo参数校验错误处理
            MethodArgumentNotValidException exs = (MethodArgumentNotValidException) ex;
            BindingResult bindingResult = exs.getBindingResult();
            StringBuilder sb = new StringBuilder();
            for(FieldError fieldError :bindingResult.getFieldErrors()){
                sb.append(fieldError.getDefaultMessage()).append("。");
            }
            errorCode = 010101;
            errorMsg = sb.toString();
            if (log.isDebugEnabled()) {
                log.debug(ERROR, ex);
            }
        } else {
//            errorMsg = ex.getMessage();
            errorMsg = "系统繁忙,请稍后再试...";
            if (log.isInfoEnabled()) {
                log.info("unprocessed exception.", ex);
            }
            response.setStatus(errorCode);
        }
        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        response.addHeader("Content-Type", "application/json;charset=utf-8");
        try {
            String resultJson="{\"success\":false,\"code\": "+errorCode+",\"msg\": \""+errorMsg+"\",\"data\": []}";
            response.getWriter().write(resultJson);
            response.flushBuffer();
        } catch (IOException e) {
            log.warn("Write data failed.", e);
        }
    }
}
