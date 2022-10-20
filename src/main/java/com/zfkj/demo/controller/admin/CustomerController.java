package com.zfkj.demo.controller.admin;

import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.dao.entity.User;
import com.zfkj.demo.service.CustomerManageService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.respvo.customer.pcCusRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Api(tags = "后台管理系统-人员管理-客户管理")
@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    CustomerManageService customerManageService;


    @ApiOperation(value = "客户列表", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/customerList")
    public Result<List<pcCusRespVo>> CustomerList(){
        return Result.success(customerManageService.customerList());
    }


    @ApiOperation(value = "查询客户", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/selectCustomer")
    public Result<List<pcCusRespVo>> selectCustomerList(@RequestParam(value = "name",required = false) String name){
        return Result.success(customerManageService.seclectCustomer(name));
    }


}
