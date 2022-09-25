package com.zfkj.demo.controller.admin;

import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.common.enums.OpenCloseEnum;
import com.zfkj.demo.dao.entity.Company;
import com.zfkj.demo.service.CompanyService;
import com.zfkj.demo.vo.basevo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "后台管理系统-企业管理-企业资源管理")
@RestController
@RequestMapping("api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @ApiOperation(value = "获取企业列表", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/getCompanyList")
    public Result<List<Company>> getCompanyList(){
        return Result.success(companyService.getCompanyList());
    }


    @ApiOperation(value = "编辑企业",notes = ApiTextHelperConstant.DEVELOPER+DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/addUpdataCompany")
    public Result<Boolean> addOrUpdateCompany(Company reqVo){
        return Result.success(companyService.addOrUpdateCompany(reqVo));
    }

    @ApiOperation(value = "查询企业", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YANGXINGYU)
    @PostMapping("/selectCompany")
    public Result<List<Company>> selectCompanyBy( String number, String adminName, String adminPhone,OpenCloseEnum status){
        return Result.success(companyService.selectCompanyBy(number,adminPhone,adminName,status));
    }


    @ApiOperation(value = "启用/停用企业", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/openCloseCompany")
    public Result openCloseCompany(@ApiParam("用户id") @RequestParam("id") Integer id,
                                @ApiParam("状态：OPEN-启用,CLOSE-禁用") @RequestParam("status") OpenCloseEnum status) {
        companyService.openCloseCompany(id,status);
        return Result.ok();
    }

    @ApiOperation(value = "删除企业", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YANGXINGYU)
    @PostMapping("/delCompany")
    public Result<Boolean> delCompany(@RequestBody List<Integer> ids){
        return Result.success( companyService.delCompany(ids));
    }

}
