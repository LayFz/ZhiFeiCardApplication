package com.zfkj.demo.controller.admin;

import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.dao.entity.TextBoard;
import com.zfkj.demo.service.TextBoardService;
import com.zfkj.demo.vo.basevo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/20 18:22
 */
@RestController
@RequestMapping("/api/manager/board")
@Api(tags = "后台管理系-留言板管理-后台管理")
@AllArgsConstructor
public class TextBoardController {
    @Autowired
    TextBoardService boardService;

    @ApiOperation(value = "回复留言板", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @PostMapping("/response")
    public Result<Boolean> responseBoard(@RequestBody TextBoard reqVo){
        return Result.success(boardService.responseBoard(reqVo));
    }

    @ApiOperation(value = "删除留言板", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @PostMapping("/delete")
    public Result<Boolean>responseBoard(@RequestBody List<Integer> reqVo){
        return Result.success(boardService.delBoard(reqVo));
    }

    @ApiOperation(value = "查询未回复留言板", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/noresponse")
    public Result<List<TextBoard>>selectNoresponse(){
        return Result.success(boardService.selectNoresponse());
    }

    @ApiOperation(value = "查询已回复留言板", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/response")
    public Result<List<TextBoard>>selectResponse(){
        return Result.success(boardService.selectResponse());
    }

    @ApiOperation(value = "查询公开留言板", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/public")
    public Result<List<TextBoard>>slectPublic(){
        return Result.success(boardService.slectPublic());
    }

    @ApiOperation(value = "查询未公开留言板", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/nopublic")
    public Result<List<TextBoard>>selctNopublic(){
        return Result.success(boardService.selctNopublic());
    }

}
