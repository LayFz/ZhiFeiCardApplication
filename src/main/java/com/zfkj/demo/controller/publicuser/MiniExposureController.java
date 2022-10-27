package com.zfkj.demo.controller.publicuser;

import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.service.MiniExposureService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.respvo.miniexposure.MiniExResVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;

@Api(tags = "MINI -名片管理-曝光统计")
@RestController
@RequestMapping("/api/user/exposure")
public class MiniExposureController {

    @Autowired
    MiniExposureService miniExposureService;


    @ApiOperation(value = "获取访客概况", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/visitProfile")
    public Result<HashMap<String,String>> visitProfile(){
        return Result.success(miniExposureService.visitorProfile());
    }

    @ApiOperation(value = "获取近七日访客趋势概况", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/sevenTrend")
    public Result<List<MiniExResVo>> sevenTrend(){
        return Result.success(miniExposureService.sevenTrend());
    }

    @ApiOperation(value = "获取历史累计", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/cumulativeDate")
    public Result<HashMap<String,String>> cumulativeDate(@RequestParam(value = "startTime",required = false)String start,@RequestParam(value = "endTime",required = false)String end){
        return Result.success(miniExposureService.cumulativeDate(start, end));
    }

    @ApiOperation(value = "获取历史日均", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/averageDate")
    public Result<HashMap<String,String>> averageDate(@RequestParam(value = "startTime",required = false)String start,@RequestParam(value = "endTime",required = false)String end){
        return Result.success(miniExposureService.averageDate(start, end));
    }

    @ApiOperation(value = "获取列表", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/miniExposureList")
    public Result<List<MiniExResVo>> miniExposureList(@RequestParam(value = "startTime",required = false)String start, @RequestParam(value = "endTime",required = false)String end){
        return Result.success(miniExposureService.miniExposureList(start,end));
    }

    @ApiOperation(value = "获取今日情况",notes =ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/todaySituation")
    public Result<HashMap<String,String>> todaySituation(){
        return Result.success(miniExposureService.todaySituation());
    }
}
