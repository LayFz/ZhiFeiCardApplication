package com.zfkj.demo.controller.admin;


import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.dao.entity.Account;
import com.zfkj.demo.service.AccountManageService;
import com.zfkj.demo.vo.basevo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "后台管理系统-设置管理-账号管理")
@RestController
@RequestMapping("/api/manager/account")
public class AccountManageController {
    @Autowired
    AccountManageService accountManageService;


    /**
     * 添加账号信息
     * @param reqVo
     * @return
     */
    @ApiOperation(value = "添加账号", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/addAccount")
    public Result<Boolean> addAccount(Account reqVo){
        return Result.success(accountManageService.addOrUpdateAccount(reqVo));
    }

    /**
     * 编辑账号信息
     * @param reqVo
     * @return
     */
    @ApiOperation(value = "编辑账号", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/updateAccount")
    public Result<Boolean> updateAccount(Account reqVo){
        return Result.success(accountManageService.addOrUpdateAccount(reqVo));
    }

    @ApiOperation(value = "删除账号",notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/delAccount")
    public Result<Boolean> delAccount(List<Integer> ids){
        return Result.success(accountManageService.delAccount(ids));
    }

    @ApiOperation(value = "重置密码", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/resetPassword")
    public Result<Boolean> resetPassword(Account reqVo){
        return Result.success(accountManageService.resetPassword(reqVo));
    }
}
