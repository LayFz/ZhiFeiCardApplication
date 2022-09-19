package com.zfkj.demo.common.config.intercepter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.zfkj.demo.common.config.redis.JedisService;
import com.zfkj.demo.common.config.AccountPropertity;
import com.zfkj.demo.common.constant.Constants;
import com.zfkj.demo.common.exception.Exceptions;
import com.zfkj.demo.common.utils.AssertUtils;
import com.zfkj.demo.vo.respvo.auth.AuthVO;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录拦截
 * @author lijunlin
 * @date 2022年1月12日
 */
@Slf4j
@Configuration
public class AuthIntercepter implements HandlerInterceptor {

    @Autowired
    private AccountPropertity accountPropertity;
    @Autowired
    private JedisService jedisService;
    @Autowired
    private IgnoreConfig ignoreConfig;

    private final PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 用户请求认证校验
        // 获取请求头 - token
        String token = request.getHeader(Constants.AUTH_HEADER);
        AssertUtils.notNull(token, Exceptions.LoginEX.NO_LOGIN);
        // 检验用户token
        String cacheObject = jedisService.getJson(Constants.LOGIN_CODE_KEY + token);
        AssertUtils.notNull(cacheObject, Exceptions.LoginEX.NO_LOGIN);
        // 判断API权限
        UserInfoVO userInfoVO = JSONUtil.toBean(cacheObject, UserInfoVO.class);
        System.out.println(userInfoVO);
        // 超级管理员直接过
        if(StrUtil.equalsAnyIgnoreCase(userInfoVO.getAccount(), accountPropertity.getAdmin())){
            return true;
        }

        // 校验该接口是否需要登录 但不需要配置api权限的
        String requestURI = request.getRequestURI();
        List<String> tokenurls = ignoreConfig.tokenurls;
        boolean checkUrl = true;
        for (String url:tokenurls){
            //当前请求url如果在登录后的白名单中，则放行。不在则校验权限url
            if (pathMatcher.match(url, requestURI)) {
                checkUrl = false;
                break;
            }
        }
        if (checkUrl){
            //校验权限url
            AssertUtils.notEmpty(userInfoVO.getApiAuth(), Exceptions.ApiEX.NO_AUTH);
            // 匹配API权限
            List<AuthVO> apiAuth = userInfoVO.getApiAuth().stream().filter(item -> StrUtil.equalsAnyIgnoreCase(requestURI,item.getUrl(),item.getApi())).collect(Collectors.toList());
            AssertUtils.notEmpty(apiAuth, Exceptions.ApiEX.NO_AUTH);
        }
        log.info("用户认证拦截器preHandle方法执行: {} ",request.getRequestURI());
        return true;
    }

}
