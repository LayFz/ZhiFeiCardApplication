package com.zfkj.demo.controller.publicuser;

import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.dao.entity.TextBoard;
import com.zfkj.demo.service.IUserInfoService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.reqvo.user.UserSaveUpdateReqVo;
import com.zfkj.demo.vo.respvo.user.QueryUserRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/19 18:00
 */
@Api(tags = "前端系统-用户注册-前端页面管理")
@RestController
@RequestMapping("/api/user")
public class RegisterController {
    @Autowired
    IUserInfoService userInfoService;


    @ApiOperation(value = "用户注册", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @PostMapping("/register")
    public Result<QueryUserRespVo> saveBoard(@Valid @RequestBody UserSaveUpdateReqVo reqVo){
        QueryUserRespVo userRespVo = userInfoService.userSaveUpdate(reqVo);
        return Result.success(userRespVo);
    }
}
