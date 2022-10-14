package com.zfkj.demo.controller.publicuser;

import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.service.MiniCusManageService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.respvo.customer.miniCusRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Api(tags = "MINI -员工管理-客户管理")
@RestController
@RequestMapping("/api/user/customer")
public class MiniCustomerController {
    @Autowired
    MiniCusManageService miniCusManageService;

    @ApiOperation(value = "获取客户总数", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/getTotal")
    public Result<Integer> getTotal(@RequestParam("cardId") int id){
        return Result.success(miniCusManageService.getAllCustomer(id));
    }

    @ApiOperation(value = "获取今日客户数", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/getTotalToday")
    public Result<Integer> getTotalToday(@RequestParam("cardId") int id){
        return Result.success(miniCusManageService.todayCustomer(id));
    }

    @ApiOperation(value = "七日新增数", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/recentSevenDays")
    public Result<Integer> recentSevenDays(@RequestParam("cardId") int id){
        return Result.success(miniCusManageService.sevenDayCustomer(id));
    }

    @ApiOperation(value = "七日趋势", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/trendSevenDays")
    public Result<HashMap<String,Integer>> trendSevenDays(@RequestParam("cardId") int id){
        return Result.success(miniCusManageService.getSevenTrend(id));
    }

    @ApiOperation(value = "客户列表", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/customerList")
    public Result<List<miniCusRespVo>> customerList(@RequestParam("cardId") int id){
        return Result.success(miniCusManageService.customerList(id));
    }

    @ApiOperation(value = "查询客户", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/selectCustomer")
    public Result<List<miniCusRespVo>> selectCustomer(@RequestParam("cardId") int id, @RequestParam(value = "phone",required = false) String phone){
        return Result.success(miniCusManageService.selectCustomer(id,phone));
    }

}
