package com.zfkj.demo.controller.publicuser;


import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.service.login.ValidateCodeService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.reqvo.user.LoginUserInfoVO;
import com.zfkj.demo.vo.respvo.auth.LoginCaptchaVO;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 登录 前端控制器
 * </p>
 *
 * @author lijunlin
 * @date 2022-1-5
 */
@Api(tags = "后台管理系统-登录-PC登录管理")
@RestController
@RequestMapping("/api/manager")
public class LoginController{

    @Autowired
    private ValidateCodeService validateCodeService;

    @ApiOperation(value = "验证码获取", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.lijunlin)
    @GetMapping("/getCode")
    public Result<LoginCaptchaVO> getAuthByUrl() {
        return Result.success(validateCodeService.createCapcha());
    }

    @ApiOperation(value = "登录", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.lijunlin)
    @PostMapping("/login")
    public Result<UserInfoVO> login(@RequestBody LoginUserInfoVO loginUserInfoVO) {
        return Result.success(validateCodeService.loginByAccountOrPhone(loginUserInfoVO));
    }

    @ApiOperation(value = "登出", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.lijunlin)
    @PostMapping("/logout")
    public Result logout() {
        validateCodeService.logout();
        return Result.success();
    }

}
