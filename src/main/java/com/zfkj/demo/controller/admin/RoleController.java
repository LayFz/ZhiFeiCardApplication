package com.zfkj.demo.controller.admin;


import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.common.enums.ForSystemEnum;
import com.zfkj.demo.service.IRoleService;
import com.zfkj.demo.vo.basevo.PageResult;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.reqvo.role.AddRoleAuthReqVo;
import com.zfkj.demo.vo.reqvo.role.AddUpdateRoleReqVo;
import com.zfkj.demo.vo.reqvo.role.QueryRoleReqVo;
import com.zfkj.demo.vo.respvo.role.RoleAuthListRespVo;
import com.zfkj.demo.vo.respvo.role.RoleRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author lijunlin
 * @since 2021-12-23
 */
@Api(tags = "后台管理系统-权限管理-角色管理")
@RestController
@RequestMapping("/api/manager/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "分页查询角色列表", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @PostMapping("/queryRole")
    public Result<PageResult<RoleRespVo>> queryRole(@RequestBody QueryRoleReqVo reqVo) {
        return Result.success(roleService.queryRole(reqVo));
    }

    @ApiOperation(value = "获取角色列表", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @GetMapping("/getRoles")
    public Result<List<RoleRespVo>> getRoles() {
        return Result.success(roleService.getRoles());
    }

    @ApiOperation(value = "新增角色", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @PostMapping("/addRole")
    public Result<Boolean> addRole(@RequestBody AddUpdateRoleReqVo reqVo){
        return Result.success(roleService.addUpdRole(reqVo));
    }

    @ApiOperation(value = "编辑角色", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @PutMapping("/updRole")
    public Result<Boolean> updRole(@RequestBody AddUpdateRoleReqVo reqVo){
        return Result.success(roleService.addUpdRole(reqVo));
    }

    @ApiOperation(value = "删除角色", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @DeleteMapping("/delRole")
    public Result<Boolean> delRole(@RequestBody List<Integer> ids) {
        return Result.success(roleService.delRole(ids));
    }

    @ApiOperation(value = "查询角色分配的权限资源集合", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @GetMapping("/roleAuthList")
    public Result<List<RoleAuthListRespVo>> roleAuthList(@RequestParam("roleIds") List<Long> roleIds,
                                                         @RequestParam(value = "forSystem",required = false) ForSystemEnum forSystem) {
        return Result.success(roleService.roleAuthList(roleIds,forSystem));
    }

    @ApiOperation(value = "角色分配权限资源", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @PostMapping("/addRoleAuth")
    public Result<Boolean> addRoleAuth(@RequestBody AddRoleAuthReqVo reqVo){
        return Result.success(roleService.addRoleAuth(reqVo));
    }

}
