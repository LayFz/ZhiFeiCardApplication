package com.zfkj.demo.common.config.intercepter;

import cn.hutool.core.util.StrUtil;
import com.zfkj.demo.common.exception.BusinessRootRuntimeException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 静态资源拦截
 *
 * @author lijunlin
 * @date 2022年5月31日
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class StaticIntercepter implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            String name = ((HandlerMethod) handler).getMethod().getName();
            if (StrUtil.equalsAnyIgnoreCase(name, "error", "errorHtml")) {
                throw new BusinessRootRuntimeException("资源不存在！");
            }
        }
    }

}
