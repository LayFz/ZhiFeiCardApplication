package com.zfkj.demo.common.config.reqloghandel;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author: lijunlin
 * @description:
 * @create: 2020-04-09 00:25
 **/
public class ServletRequestLogConfiguration {
    public ServletRequestLogConfiguration() {
    }

    @Bean
    public FilterRegistrationBean webDefaultFilter() {
        FilterRegistrationBean<RequestLogFilter> registration = new FilterRegistrationBean(new RequestLogFilter(), new ServletRegistrationBean[0]);
        registration.addUrlPatterns(new String[]{"/*"});
        return registration;
    }
}
