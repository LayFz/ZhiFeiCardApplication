package com.zfkj.demo.controller.admin;

import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.service.PcExposureService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.reqvo.pcexposure.DePartReVo;
import com.zfkj.demo.vo.respvo.pcexposure.DepartResVo;
import com.zfkj.demo.vo.respvo.pcexposure.ExpersonResVo;
import com.zfkj.demo.vo.respvo.pcexposure.PcExResVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Api(tags = "后台管理系统-首页-曝光统计")
@RestController
@RequestMapping("/api/homepage/exposure")
public class HomePageController {

    @Autowired
    PcExposureService pcExposureService;

    @ApiOperation(value = "获取名片曝光", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/cardExposure")
    public Result<List<PcExResVo>> cardExposure(@RequestParam(value = "startMonth",required = false) String startMonth, @RequestParam(value = "endMonth",required = false) String endMonth){
        return Result.success(pcExposureService.cardExposure(startMonth, endMonth));
    }

    @ApiOperation(value = "获取客户数量", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/customersNumber")
    public Result<List<PcExResVo>> customersNumber(@RequestParam(value = "startMonth",required = false) String startMonth, @RequestParam(value = "endMonth",required = false) String endMonth){
        return Result.success(pcExposureService.customersNumber(startMonth, endMonth));
    }

    @ApiOperation(value = "获取本月优秀员工", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/personExcellent")
    public Result<List<ExpersonResVo>> personExcellent(){
        return Result.success(pcExposureService.personExcellent());
    }

    @ApiOperation(value = "部门统计", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/departmentStatistics")
    public Result<List<DepartResVo>> departmentStatistics(@RequestBody DePartReVo reVo){
        return Result.success(pcExposureService.departmentStatistics(reVo));
    }
}
