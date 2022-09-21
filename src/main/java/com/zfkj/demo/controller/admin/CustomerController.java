package com.zfkj.demo.controller.admin;

import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.dao.entity.User;
import com.zfkj.demo.service.CustomerManageService;
import com.zfkj.demo.vo.basevo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Api(tags = "后台管理系统-客户管理-客户资源管理")
@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    CustomerManageService customerManageService;


    @ApiOperation(value = "获取客户列表", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/getcustomerList")
    public Result<List<User>> getAllCustomerBytime(){
        return Result.success(customerManageService.getAllCustomerBytime());
    }

    @ApiOperation(value = "获取客户总数", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/getallcustomer")
    public Result<Integer> getAllCustomer(){
        return Result.success(customerManageService.getAllCustomer());
    }

    @ApiOperation(value = "获取七日新增", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/getsevencustomer")
    public Result<Integer> sevenDayCustomer(){
        return Result.success(customerManageService.sevenDayCustomer());
    }


    @ApiOperation(value = "获取今日新增", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/gettodaycustomer")
    public Result<Integer> todayCustomer(){
        return Result.success(customerManageService.todayCustomer());
    }

    @ApiOperation(value = "根据电话查询客户", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @PostMapping("/getcustomerByphone")
    public Result<User> selectPhoneByUser(String phone){
        return Result.success(customerManageService.selectPhoneByUser(phone));
    }

    @ApiOperation(value = "获取七天趋势", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/getSevenTrend")
    public Result<HashMap<String, Integer>> getSevenTrend(){
        return Result.success(customerManageService.getSevenTrend());
    }
}
