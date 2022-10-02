package com.zfkj.demo.controller.admin;


import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.service.BannerService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.reqvo.article.AddUpArticleVo;
import com.zfkj.demo.vo.reqvo.banner.SaveBannerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "后台管理系统-个性化管理-配置管理")
@RestController
@RequestMapping("/api/manager/banner")
public class BannerController {

    @Autowired
    BannerService bannerService;

    @ApiOperation(value = "保存个性化简介配置", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/saveIntroBanner")
    public Result<Boolean> SaveIntroBanner(SaveBannerVo ReVo){
        return Result.success(bannerService.saveIntroductBanner(ReVo));
    }

    @ApiOperation(value = "保存个性化内容配置", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/saveContentBanner")
    public Result<Boolean> SaveContentBanner(SaveBannerVo ReVo){
        return Result.success(bannerService.saveContentBanner(ReVo));
    }
}
