package com.zfkj.demo.common.utils;

import cn.hutool.json.JSONUtil;
import com.zfkj.demo.common.config.redis.JedisService;
import com.zfkj.demo.common.constant.Constants;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息
 *
 * @author lijunlin
 * @date 2022年1月10日
 */
@Component
public class SystemUserUtil {
    @Autowired
    private JedisService jedisService;

    private UserInfoVO userInfoVO;

    public void setUserInfoVO(UserInfoVO userInfoVO) {
        this.userInfoVO = userInfoVO;
    }

    /**
     * 生成redis token key
     *
     * @param token
     * @return
     */
    private String getTokenKey(String token) {
        return Constants.LOGIN_CODE_KEY + token;
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public UserInfoVO getLoginUser() {
        if(userInfoVO == null){
            HttpServletRequest request = WebUtil.getRequest();
            // 获取请求携带的令牌
            String token = request.getHeader(Constants.AUTH_HEADER);
            if (StringUtils.isEmpty(token)) {
                return null;
            }
            String userKey = getTokenKey(token);
            String userStr = jedisService.getJson(userKey);
            return JSONUtil.toBean(userStr, UserInfoVO.class);
        }else {
            return userInfoVO;
        }
    }
}
