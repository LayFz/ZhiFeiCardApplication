package com.zfkj.demo.controller.admin;


import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.service.IAuthService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.reqvo.auth.AuthAddUpdateReqVo;
import com.zfkj.demo.vo.respvo.auth.AuthTreeTopRespVo;
import com.zfkj.demo.vo.respvo.role.RoleRespVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单资源表 前端控制器
 * </p>
 *
 * @author lijunlin
 * @since 2021-12-23
 */
@Api(tags = "后台管理系统-权限管理-权限资源管理")
@RestController
@RequestMapping("/api/manager/auth")
public class AuthController{

    @Autowired
    private IAuthService authService;


    @ApiOperation(value = "查询url资源权限", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @GetMapping("/getAuthByUrl")
    public Result<String> getAuthByUrl(@RequestParam("url") String url) {
        return Result.success(authService.getAuthByUrl(url));
    }

    @ApiOperation(value = "获取权限资源树", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @GetMapping("/getAuthTree")
    public AuthTreeTopRespVo getAuthTree(@ApiParam("资源所属系统 WEB-后台管理系统权限、APP-app权限、MINI-小程序权限")
                                         @RequestParam("clientType") String clientType) {
        return authService.getAuthTree(clientType);
    }

    @ApiOperation(value = "添加权限资源", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @PostMapping("/addAuth")
    public Result<Boolean> addAuth(@RequestBody AuthAddUpdateReqVo reqVo) {
        return Result.success(authService.addOrUpdateAuth(reqVo));
    }

    @ApiOperation(value = "修改权限资源", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @PutMapping("/updateAuth")
    public Result<Boolean> updateAuth(@RequestBody AuthAddUpdateReqVo reqVo) {
        return Result.success(authService.addOrUpdateAuth(reqVo));
    }

    @ApiOperation(value = "批量删除权限资源", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @DeleteMapping("/delAuthByIds")
    public Result<Boolean> delAuthByIds(@RequestBody List<Integer> ids) {
        return Result.success(authService.delAuthByIds(ids));
    }

}
