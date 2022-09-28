package com.zfkj.demo.controller.admin;

import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.dao.entity.Organize;
import com.zfkj.demo.dao.entity.User;
import com.zfkj.demo.service.OrganizationService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.respvo.organize.OrganizationVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/26 16:52
 */

@Api(tags = "后台管理系统-组织结构管理-设置资源管理")
@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;


    @ApiOperation(value = "添加父级", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @PostMapping("/addheadorganization")
    public Result<Boolean> addHeadOrganization(@RequestBody Organize organize){
        return Result.success(organizationService.addOrSaveHeadOrganization(organize));
    }

    @ApiOperation(value = "修改父级", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @PutMapping("/editheadorganization")
    public Result<Boolean> editHeadOrganization(@RequestBody Organize organize){
        return Result.success(organizationService.addOrSaveHeadOrganization(organize));
    }
    @ApiOperation(value = "删除子集或者父级（父级删除将会删除子集）", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/delorganize")
    public Result<Boolean> editHeadOrganization(@RequestParam int id){
        return Result.success(organizationService.delOrganization(id));
    }
    @ApiOperation(value= "查询整个公司的结构组织（注意要以公司管理员账号登陆）", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/getorganzition")
    public Result<List<OrganizationVo>> selectOrganization(){
        return Result.success(organizationService.getOrganization());
    }
}
