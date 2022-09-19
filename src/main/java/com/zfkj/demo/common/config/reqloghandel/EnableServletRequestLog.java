package com.zfkj.demo.common.config.reqloghandel;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: lijunlin
 * @description:
 * @create: 2020-04-09 00:22
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
@Import({EnableServletRequestLogImportSelector.class})
public @interface EnableServletRequestLog {
}
