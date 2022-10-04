package com.zfkj.demo.controller.admin;

import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.service.StaffService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.reqvo.staff.AddUpStaffReVo;
import com.zfkj.demo.vo.reqvo.staff.DelStaffReVo;
import com.zfkj.demo.vo.respvo.staff.StaffRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "后台管理系统-人员-员工管理")
@RestController
@RequestMapping("/api/manager/staff")
public class StaffController {

    @Autowired
    StaffService staffService;

    @ApiOperation(value = "获取员工数据列表", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/getStaffList")
    public Result<List<StaffRespVo>> getStaffList(){
        return Result.success(staffService.getStaffList());
    }

    @ApiOperation(value = "添加员工", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/addStaff")
    public Result<Boolean> addStaff(@RequestBody AddUpStaffReVo reVo){
        return  Result.success(staffService.addStaff(reVo));
    }

    @ApiOperation(value = "编辑员工", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/updataStaff")
    public Result<Boolean> updataStaff(@RequestBody AddUpStaffReVo reVo){
        return  Result.success(staffService.updataStaff(reVo));
    }

    @ApiOperation(value = "删除员工", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/deleteStaff")
    public Result<Boolean> deleteStaff(@RequestBody DelStaffReVo reVo){
        return  Result.success(staffService.delStaff(reVo));
    }
}
