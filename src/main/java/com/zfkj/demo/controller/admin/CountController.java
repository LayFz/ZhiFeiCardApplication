package com.zfkj.demo.controller.admin;

import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.dao.entity.TextBoard;
import com.zfkj.demo.dao.repository.TextBoardRepository;
import com.zfkj.demo.service.TextBoardService;
import com.zfkj.demo.vo.basevo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/9/20 17:21
 */
@RestController
@RequestMapping("/api/manager/count")
@Api(tags = "后台管理系统-数据相关-数据统计")
@AllArgsConstructor
public class CountController {
    @Autowired
    TextBoardService boardService;

    @ApiOperation(value = "获取留言板概况信息", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/board")
    public Result<HashMap<String,String>>getBoardDate(){
        return Result.success(boardService.getBoardDate());
    }
}
