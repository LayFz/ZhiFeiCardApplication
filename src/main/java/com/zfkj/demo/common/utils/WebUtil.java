package com.zfkj.demo.common.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * web工具类+
 * @author lijunlin
 * @date 2022年1月5日
 */
public class WebUtil {
    /**
     * 获取上下文
     */
    public static ServletRequestAttributes contextHolder() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public static HttpServletRequest getRequest() {
        return contextHolder().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return contextHolder().getResponse();
    }

    /**
     * session
     */
    public static Object getSessionByAttr(String name) {
        return getRequest().getSession().getAttribute(name);
    }

    public static void setSessionAttr(String name, Object val) {
        getRequest().getSession().setAttribute(name, val);
    }

    public static void removeSessionAttr(String key) {
        getRequest().getSession().removeAttribute(key);
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name, String defaultValue) {
        return Convert.toStr(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name) {
        return Convert.toInt(getRequest().getParameter(name));
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name, Integer defaultValue) {
        return Convert.toInt(getRequest().getParameter(name), defaultValue);
    }


    /**
     * 获取session
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static void renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(HttpStatus.HTTP_OK);
            response.setContentType(ContentType.JSON.getValue());
            response.setCharacterEncoding(CharsetUtil.UTF_8);
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) {
            for (String s : strs) {
                if (str.contains(s)) {
                    return true;
                }
            }
        }
        return false;
    }

}
