package com.zfkj.demo.controller.admin;

import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.dao.entity.Article;
import com.zfkj.demo.service.ArticleService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.reqvo.auth.AuthAddUpdateReqVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/20 14:18
 */
@Api(tags = "后台管理系统-简介文章管理-文件资源管理")
@RestController
@RequestMapping("/api/manager/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @ApiOperation(value = "添加公司简介文章", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @PostMapping("/add")
    public Result<Boolean> addArticle(@RequestBody Article reqVo){
        return Result.success(articleService.addOrUpdateArticle(reqVo));
    }

    @ApiOperation(value = "删除公司简介文章", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @DeleteMapping("/delete")
    public Result<Boolean> delArticle(@RequestBody List<Integer> ids){
        return Result.success(articleService.delArticle(ids));
    }

    @ApiOperation(value = "修改公司简介文章", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @PutMapping("/update")
    public Result<Boolean> updateArticle(@RequestBody Article reqVo) {
        return Result.success(articleService.addOrUpdateArticle(reqVo));
    }

    @ApiOperation(value = "查看公司简介文章", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/select")
    public Result<List<Article>> updateArticle() {
        return Result.success(articleService.selectArticle());
    }
}
