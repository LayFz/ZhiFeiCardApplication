package com.zfkj.demo.service.login.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.zfkj.demo.common.config.redis.JedisService;
import com.zfkj.demo.common.constant.Constants;
import com.zfkj.demo.common.exception.Exceptions;
import com.zfkj.demo.dao.repository.UserRoleRepository;
import com.zfkj.demo.service.IUserInfoService;
import com.zfkj.demo.service.login.ValidateCodeService;
import com.zfkj.demo.common.utils.AssertUtils;
import com.zfkj.demo.common.utils.SystemUserUtil;
import com.zfkj.demo.common.utils.WebUtil;
import com.zfkj.demo.vo.reqvo.user.LoginUserInfoVO;
import com.zfkj.demo.vo.respvo.auth.LoginCaptchaVO;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 验证码登录逻辑实现处理
 *
 * @author pi
 */
@Service
@Slf4j
@AllArgsConstructor
public class ValidateCodeServiceImpl implements ValidateCodeService {

    private final JedisService jedisService;
    private final IUserInfoService iUserInfoService;
    private final SystemUserUtil systemUserUtil;
    private final UserRoleRepository userRoleRepository;

    /**
     * 生成验证码
     */
    @Override
    public LoginCaptchaVO createCapcha() {
        log.info("获取验证码");
        HttpServletResponse response = WebUtil.getResponse();
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/png");

        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(120, 40);
        lineCaptcha.setGenerator(randomGenerator);

        //输出code
        String uuid = IdUtil.simpleUUID();
        String key = Constants.CAPTCHA_CODE_KEY + uuid;
        // 保存验证码信息
        String code = lineCaptcha.getCode();
        jedisService.setJson(key, code, Constants.CAPTCHA_EXPIRATION);

        LoginCaptchaVO loginCaptchaVO = new LoginCaptchaVO();
        loginCaptchaVO.setCaptchaId(uuid);
        // TODO 临时添加code到接口数据中 方便调试
        loginCaptchaVO.setCaptchaCode(code);
        loginCaptchaVO.setImage("data:image/png;base64," + lineCaptcha.getImageBase64());
        return loginCaptchaVO;
    }

    /**
     * 校验验证码
     */
    @Override
    public void checkCapcha(String code, String uuid) {
        AssertUtils.notNull(code, Exceptions.LoginEX.CAPTCHA_EXPIRE);
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = jedisService.getJson(verifyKey);
        jedisService.delKey(verifyKey);
        AssertUtils.isTrue(StrUtil.equalsIgnoreCase(code, captcha), Exceptions.LoginEX.CAPTCHA_ERROR);
    }

    /**
     * 用户登录
     * 根据账号 以及 手机号登录
     *
     * @param loginUserInfoVO
     */
    @Override
    public UserInfoVO loginByAccountOrPhone(LoginUserInfoVO loginUserInfoVO) {
        // 校验验证码
//        checkCapcha(loginUserInfoVO.getCaptchCode(), loginUserInfoVO.getCaptchId());
        // 用户信息查询
        UserInfoVO userInfo = iUserInfoService.loginByAccountOrPhone(loginUserInfoVO);

        //  生成token
        String token = IdUtil.simpleUUID();
        userInfo.setToken(token);
        // 保存用户信息
        String key = Constants.LOGIN_CODE_KEY + token;
        jedisService.setJson(key, JSONUtil.toJsonStr(userInfo), Constants.TOKEN_EXPIRE);
        systemUserUtil.setUserInfoVO(userInfo);
        return userInfo;
    }

    @Override
    public void logout() {
        UserInfoVO loginUser = systemUserUtil.getLoginUser();
        String tokenKey = loginUser.getToken();
        jedisService.delKey(tokenKey);
        // 置空当前用户信息
        systemUserUtil.setUserInfoVO(null);
    }
}
