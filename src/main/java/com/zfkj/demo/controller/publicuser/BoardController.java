package com.zfkj.demo.controller.publicuser;

import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.dao.entity.TextBoard;
import com.zfkj.demo.service.TextBoardService;
import com.zfkj.demo.vo.basevo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.soap.Text;
import java.util.List;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/19 12:36
 */
@Api(tags = "前端系统-上传留言板-前端页面")
@RestController
@RequestMapping("/api/user")
public class BoardController {
    @Autowired
    TextBoardService textBoardService;

    @ApiOperation(value = "提交留言", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @PostMapping("/saveboard")
    public Result<Boolean> saveBoard(@RequestBody TextBoard reqVo){
        return Result.success(textBoardService.saveboard(reqVo));
    }


    @ApiOperation(value = "查询首页留言（该接口只对普通用户）", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/getboard")
    public Result<List<TextBoard>> getHomeBoard(){
        return Result.success(textBoardService.gethomeBoard());
    }




}
