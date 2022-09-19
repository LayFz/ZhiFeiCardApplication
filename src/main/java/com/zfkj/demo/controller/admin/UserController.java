package com.zfkj.demo.controller.admin;


import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.common.enums.ForSystemEnum;
import com.zfkj.demo.common.enums.OpenCloseEnum;
import com.zfkj.demo.service.IUserInfoService;
import com.zfkj.demo.vo.basevo.LoginUser;
import com.zfkj.demo.vo.basevo.PageResult;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.reqvo.user.AddUserRolesReqVo;
import com.zfkj.demo.vo.reqvo.user.ImportUserReqVo;
import com.zfkj.demo.vo.reqvo.user.QueryUserReqVo;
import com.zfkj.demo.vo.reqvo.user.UserSaveUpdateReqVo;
import com.zfkj.demo.vo.respvo.role.RoleAuthListRespVo;
import com.zfkj.demo.vo.respvo.user.QueryUserRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.compress.utils.Lists;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author lijunlin
 * @since 2021-12-23
 */
@Api(tags = "后台管理系统-权限管理-用户管理")
@RestController
@RequestMapping("/api/manager/user")
public class UserController {

    @Autowired
    private IUserInfoService userService;

    @ApiOperation(value = "分页查询用户列表", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @PostMapping("/queryUser")
    public Result<PageResult<QueryUserRespVo>> queryUser(@RequestBody QueryUserReqVo reqVo) {
        //通过mybatis plus的分页插件实现分页
        return Result.success(userService.queryUser(reqVo));
    }

    @ApiOperation(value = "保存用户", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @PostMapping("/userSave")
    public Result<QueryUserRespVo> userSave(@Valid @RequestBody UserSaveUpdateReqVo reqVo) {
        QueryUserRespVo userRespVo = userService.userSaveUpdate(reqVo);
        return Result.success(userRespVo);
    }

    @ApiOperation(value = "修改用户", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @PutMapping("/userUpdate")
    public Result<QueryUserRespVo> userUpdate(@Valid @RequestBody UserSaveUpdateReqVo reqVo) {
        QueryUserRespVo userRespVo = userService.userSaveUpdate(reqVo);
        return Result.success(userRespVo);
    }


    @ApiOperation(value = "根据id查询用户信息", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @GetMapping("/selectUserById")
    public Result<LoginUser> selectUserById(@RequestParam("id") Integer id) {
        LoginUser loginUser = userService.selectUserById(id);
        return Result.success(loginUser);
    }

    @ApiOperation(value = "启用/禁用用户", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @GetMapping("/openCloseUser")
    public Result openCloseUser(@ApiParam("用户id") @RequestParam("id") Integer id,
                                @ApiParam("状态：OPEN-启用,CLOSE-禁用") @RequestParam("status") OpenCloseEnum status) {
        userService.openCloseUser(id,status);
        return Result.ok();
    }

    @ApiOperation(value = "删除用户", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @DeleteMapping("/delUsers")
    public Result delUsers(@RequestBody List<Integer> ids) {
        userService.delUsers(ids);
        return Result.success();
    }

    @ApiOperation(value = "用户分配角色", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @PostMapping("/addUserRoles")
    public Result<Boolean> addUserRoles(@RequestBody AddUserRolesReqVo reqVo){
        return Result.success(userService.addUserRoles(reqVo));
    }

    @ApiOperation(value = "获取用户菜单资源", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @GetMapping("/getUserMenus")
    public Result<List<RoleAuthListRespVo>> getUserMenus(@RequestParam("forSystem") ForSystemEnum forSystem) {
        return Result.success(userService.getUserMenus(forSystem));
    }

    @ApiOperation(value = "导入用户", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @PostMapping("/importUser")
    public Result<Boolean> importUser(@RequestParam("file") MultipartFile file) throws IOException {
        userService.importUser(file);
        return Result.success();
    }

    @ApiOperation(value = "导出模板", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @PostMapping("/exportTemplete")
    public void exportUser() throws IOException {
        userService.exportTemplete(Lists.newArrayList(), ImportUserReqVo.class);
    }

    @ApiOperation(value = "导出用户", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIJUNLIN)
    @PostMapping("/exportUser")
    public void exportUser(@RequestBody QueryUserReqVo reqVo) throws IOException {
        userService.exportUser(reqVo);
    }

}
