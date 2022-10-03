package com.zfkj.demo.controller.admin;


import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.dao.entity.User;
import com.zfkj.demo.service.AccountManageService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.reqvo.account.*;
import com.zfkj.demo.vo.respvo.account.accountRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "后台管理系统-设置管理-账号管理")
@RestController
@RequestMapping("/api/manager/account")
public class AccountManageController {
    @Autowired
    AccountManageService accountManageService;


    /**
     * 获取账号信息
     */
    @ApiOperation(value = "获取账号列表", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/getAccountList")
    public Result<List<accountRespVo>> getAccountList(){
        return Result.success(accountManageService.getAccountList());
    }

    /**
     * 获取账号
     */
    @ApiOperation(value = "查询账号", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/selectAccount")
    public Result<List<accountRespVo>> selectAccountList(@RequestParam("search") String reVo){
        return Result.success(accountManageService.selectAccount(reVo));
    }
    /**
     * 添加账号信息
     * @param reqVo
     * @return
     */
    @ApiOperation(value = "添加账号", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/addAccount")
    public Result<Boolean> addAccount(@RequestBody AddUpAccountVo reqVo){
        return Result.success(accountManageService.addAccount(reqVo));

    }

    /**
     * 编辑账号信息
     * @param reqVo
     * @return
     */
    @ApiOperation(value = "编辑账号", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/updateAccount")
    public Result<Boolean> updateAccount(@RequestBody UpdateAccountVo reqVo){
        return Result.success(accountManageService.UpdateAccount(reqVo));

    }

    @ApiOperation(value = "删除账号",notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/delAccount")
    public Result<Boolean> delAccount(@RequestBody DelAccountVo reqvo){
        return Result.success(accountManageService.delAccount(reqvo));
    }

    @ApiOperation(value = "重置密码", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/resetPassword")
    public Result<Boolean> resetPassword(@RequestBody RePassAccountVo reqVo){
        return Result.success(accountManageService.resetPassword(reqVo));
    }
}
