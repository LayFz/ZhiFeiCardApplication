package com.zfkj.demo.controller.admin;

import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.dao.entity.CompanyIntro;
import com.zfkj.demo.service.CompanyIntroService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.reqvo.articleClassify.delArcClassifyId;
import com.zfkj.demo.vo.reqvo.articleClassify.saveArcClassifyReVo;
import com.zfkj.demo.vo.reqvo.articleClassify.upDownArcClassifyReVo;
import com.zfkj.demo.vo.reqvo.companyIntro.delIntroReVo;
import com.zfkj.demo.vo.reqvo.companyIntro.saveIntroReVo;
import com.zfkj.demo.vo.reqvo.companyIntro.upDownIntroReVo;
import com.zfkj.demo.vo.respvo.articleClassify.articleClassifyRespVo;
import com.zfkj.demo.vo.respvo.companyIntro.introRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "后台管理系统-个性化简介-分类管理")
@RestController
@RequestMapping("api/manager/companyIntro")
public class CompanyIntroController {

    @Autowired
    CompanyIntroService companyIntroService;

    @ApiOperation(value = "获取分类数据列表", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/getIntroList")
    public Result<List<introRespVo>> getIntroList(){
        return Result.success(companyIntroService.getCompanyIntroList());
    }

    @ApiOperation(value = "添加分类", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/addIntro")
    public Result<Boolean> addIntro(@RequestBody CompanyIntro reVo){
        return Result.success(companyIntroService.addCompanyIntro(reVo));
    }

    @ApiOperation(value = "编辑分类", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/updataIntro")
    public Result<Boolean> updataIntro(@RequestBody saveIntroReVo reVo){
        return Result.success(companyIntroService.updataCompanyIntro(reVo));
    }

    @ApiOperation(value = "删除简介", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/delIntro")
    public Result<Boolean> delIntro(@RequestBody delIntroReVo reVo){
        return Result.success(companyIntroService.delCompanyIntro(reVo));
    }

    @ApiOperation(value = "上移", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/upLevel")
    public Result<Boolean> upLevel(@RequestBody upDownIntroReVo reVo){
        return Result.success(companyIntroService.upIntroLevel(reVo));
    }

    @ApiOperation(value = "下移", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/downLevel")
    public Result<Boolean> downLevel(@RequestBody upDownIntroReVo reVo){
        return Result.success(companyIntroService.downIntroLevel(reVo));
    }
}
