package com.zfkj.demo.service.login;


import com.zfkj.demo.vo.reqvo.user.LoginUserInfoVO;
import com.zfkj.demo.vo.respvo.auth.LoginCaptchaVO;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;

/**
 * 验证码与用户登录逻辑处理
 *
 * @author lijunlin
 */
public interface ValidateCodeService {
    /**
     * 生成验证码
     */
    LoginCaptchaVO createCapcha();

    /**
     * 校验验证码
     */
    void checkCapcha(String key, String value);

    UserInfoVO loginByAccountOrPhone(LoginUserInfoVO loginUserInfoVO);

    void logout();
}
