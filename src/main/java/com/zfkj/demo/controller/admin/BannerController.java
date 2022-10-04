package com.zfkj.demo.controller.admin;


import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.dao.entity.Banner;
import com.zfkj.demo.service.BannerService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.reqvo.article.AddUpArticleVo;
import com.zfkj.demo.vo.reqvo.banner.SaveBannerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "后台管理系统-个性化管理-配置管理")
@RestController
@RequestMapping("/api/manager/banner")
public class BannerController {

    @Autowired
    BannerService bannerService;

    @ApiOperation(value = "保存个性化简介配置", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/saveIntroBanner")
    public Result<Boolean> SaveIntroBanner(@RequestBody SaveBannerVo ReVo){
        return Result.success(bannerService.saveIntroductBanner(ReVo));
    }

    @ApiOperation(value = "保存个性化内容配置", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/saveContentBanner")
    public Result<Boolean> SaveContentBanner(@RequestBody SaveBannerVo ReVo){
        return Result.success(bannerService.saveContentBanner(ReVo));
    }

    @ApiOperation(value = "开启关闭个性化简介配置", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/changeIntroBanner")
    public Result<Boolean> ChangeIntroBanner(){
        return Result.success(bannerService.ChangeIntroBanner());
    }

    @ApiOperation(value = "开启关闭化内容配置", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/changeContentBanner")
    public Result<Boolean> ChangeContentBanner(){
        return Result.success(bannerService.changeContentBanner());
    }



    @ApiOperation(value = "获取Banner的信息", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @PostMapping("/getBanners")
    public Result<List<Banner>> SaveContentBanner(){
        return Result.success(bannerService.getBanners());
    }
}
