package com.zfkj.demo.controller.admin;


import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.service.IBaseDictService;
import com.zfkj.demo.vo.basevo.PageResult;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.reqvo.dict.BaseDictReqVO;
import com.zfkj.demo.vo.reqvo.dict.DictListReqVO;
import com.zfkj.demo.vo.respvo.dict.BaseDictRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 基础字典信息表 前端控制器
 * </p>
 *
 * @author lijunlin
 * @since 2022-01-07
 */
@RestController
@RequestMapping("/api/manager/baseDict")
@Api(tags = "后台管理系统-权限管理-字典管理")
@AllArgsConstructor
public class BaseDictController {
    private final IBaseDictService iBaseDictService;

    @ApiOperation(value = "新增字典数据", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.lijunlin)
    @PostMapping("/addDict")
    public Result<Boolean> addDict(@RequestBody BaseDictReqVO dictVO) {
        return Result.success(iBaseDictService.addDict(dictVO));
    }

    @ApiOperation(value = "修改字典数据", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.lijunlin)
    @PutMapping("/editDict")
    public Result<Boolean> editDict(@RequestBody BaseDictReqVO dictVO) {
        iBaseDictService.editDict(dictVO);
        return Result.success();
    }

    @ApiOperation(value = "字典数据分页查询", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.lijunlin)
    @PostMapping("/getDictList")
    public Result<PageResult<BaseDictRespVO>> getDictList(@RequestBody DictListReqVO dictVO) {
        return Result.success(iBaseDictService.getDictList(dictVO));
    }

    @ApiOperation(value = "根据字典标识获取字典数据", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.lijunlin)
    @GetMapping("/getDictCode")
    public Result<List<BaseDictRespVO>> getDictCode(@ApiParam("字典标识") @RequestParam("identifying") String identifying) {
        return Result.success(iBaseDictService.getDictCode(identifying));
    }

    @ApiOperation(value = "根据ID删除字典数据", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.lijunlin)
    @DeleteMapping("/deleteDict")
    public Result<String> deleteDict(@RequestBody List<Long> ids) {
        iBaseDictService.deleteDict(ids);
        return Result.success();
    }
}
