package com.zfkj.demo.controller.publicuser;

import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.dao.entity.Article;
import com.zfkj.demo.dao.entity.ArticleClassify;
import com.zfkj.demo.dao.entity.ArticleManage;
import com.zfkj.demo.dao.entity.CompanyIntro;
import com.zfkj.demo.service.ArticleClassifyService;
import com.zfkj.demo.service.ArticleService;
import com.zfkj.demo.service.CompanyIntroService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.reqvo.article.ArticleReqVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/19 12:36
 */
@Api(tags = "MINI小程序-获取公司简介列表-公司简介")
@RestController
@RequestMapping("/api/user/content")
public class ContenetController {
    @Autowired
    CompanyIntroService companyIntroService;

    @Autowired
    ArticleClassifyService articleClassifyService;

    @Autowired
    ArticleService articleService;

    @ApiOperation(value = "获取公司简介信息", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/getContentIntroTitle")
    public Result<List<CompanyIntro>> getIntroTitle(@RequestParam(value = "cardId", required = false) Integer card_id) {
        return Result.success(companyIntroService.getIntroTitle(card_id));
    }

    @ApiOperation(value = "获取公司案例信息", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/getContentTitle")
    public Result<List<ArticleClassify>> getContentTitle(@RequestParam(value = "cardId", required = false) Integer card_id) {
        return Result.success(articleClassifyService.getContentTitle(card_id));
    }

    @ApiOperation(value = "根据分类ID获取指定文章列表", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/getArticleList")
    public Result<List<ArticleManage>> getArticleListById(@RequestParam(value = "calssfy_id") Integer classfy_id) {
        return Result.success(articleService.getArticleListById(classfy_id));
    }

    @ApiOperation(value = "根据ID获取指定文章", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/getArticleById")
    public Result<ArticleManage> getArticleById(@RequestParam(value = "article_id") Integer article_id) {
        return Result.success(articleService.getArticleById(article_id));
    }

}
