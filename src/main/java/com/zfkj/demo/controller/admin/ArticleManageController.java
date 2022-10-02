package com.zfkj.demo.controller.admin;


import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.service.ArticleManageService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.reqvo.article.AddUpArticleVo;
import com.zfkj.demo.vo.reqvo.article.DelArticleVo;
import com.zfkj.demo.vo.reqvo.article.ArticleReqVo;
import com.zfkj.demo.vo.respvo.article.articleByVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "后台管理系统-个性化内容-文章管理")
@RestController
@RequestMapping("/api/manager/articlemanage")
public class ArticleManageController {
    @Autowired
    ArticleManageService articleManageService;


    @ApiOperation(value = "获取文章数据列表", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/getArticleList")
    public Result<List<ArticleReqVo>> getArticleList(){
        return Result.success(articleManageService.getArticleData());
    }

    @ApiOperation(value = "查询文章", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/selectArticleList")
    public Result<List<ArticleReqVo>> SelectArticleList(articleByVo ByVo){
        return Result.success(articleManageService.selectArticle(ByVo));
    }


    @ApiOperation(value = "新增文章", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/addArticle")
    public Result<Boolean> AddArticle(AddUpArticleVo reVo){
        return Result.success(articleManageService.addArticle(reVo));
    }

    @ApiOperation(value = "编辑文章", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/updateArticle")
    public Result<Boolean> UpdateArticle(AddUpArticleVo reVo){
        return Result.success(articleManageService.updataArticle(reVo));
    }

    @ApiOperation(value = "删除文章", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/deleteArticle")
    public Result<Boolean> DeleteArticle(DelArticleVo reVo){
        return Result.success(articleManageService.DelArticle(reVo));
    }
}
