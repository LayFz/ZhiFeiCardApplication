package com.zfkj.demo.controller.admin;

import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.dao.entity.ArticleClassify;
import com.zfkj.demo.service.ArticleClassifyService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.reqvo.articleClassify.delArcClassifyId;
import com.zfkj.demo.vo.reqvo.articleClassify.saveArcClassifyReVo;
import com.zfkj.demo.vo.reqvo.articleClassify.upDownArcClassifyReVo;
import com.zfkj.demo.vo.respvo.articleClassify.articleClassifyRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "后台管理系统-个性化内容-分类管理")
@RestController
@RequestMapping("/api/manager/articleClassify")
public class ArticleClassifyController {
    @Autowired
    ArticleClassifyService classifyService;

    @ApiOperation(value = "获取分类数据列表", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/getClassifyList")
    public Result<List<ArticleClassify>> getClassifyList(){
        return Result.success(classifyService.getArticleClassify());
    }

    @ApiOperation(value = "添加分类", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/addClassify")
    public Result<Boolean> addClassifyList(@RequestBody ArticleClassify reVo){
        return Result.success(classifyService.addArticleClassify(reVo));
    }

    @ApiOperation(value = "编辑分类", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/updataClassify")
    public Result<Boolean> updataClassifyList(@RequestBody ArticleClassify reVo){
        return Result.success(classifyService.updataArticleClassify(reVo));
    }

    @ApiOperation(value = "删除分类", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/delClassify")
    public Result<Boolean> delClassifyList(@RequestBody  delArcClassifyId ReVo){
        return Result.success(classifyService.delArticleClassify(ReVo));
    }
    @ApiOperation(value = "上移", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/upLevel")
    public Result<Boolean> upLevel(@RequestBody upDownArcClassifyReVo reVo){
        return Result.success(classifyService.upLevel(reVo));
    }

    @ApiOperation(value = "下移", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/downLevel")
    public Result<Boolean> downLevel(@RequestBody upDownArcClassifyReVo reVo){
        return Result.success(classifyService.DownLevel(reVo));
    }

}
