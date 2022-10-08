package com.zfkj.demo.controller.publicuser;

import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.dao.entity.Banner;
import com.zfkj.demo.service.BannerService;
import com.zfkj.demo.vo.basevo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/10/4 16:03
 */
@Api(tags = "MINI小程序-获取Banner-首页")
@RestController
@RequestMapping("/api/user/")
public class MiniBannerController {
    @Autowired
    BannerService bannerService;


    @ApiOperation(value = "获取Banner的信息", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/getBanners")
    public Result<List<Banner>> SaveContentBanner(@RequestParam("company_id")int id){
        return Result.success(bannerService.getBannersById(id));
    }
}
