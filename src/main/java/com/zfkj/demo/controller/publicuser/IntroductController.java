package com.zfkj.demo.controller.publicuser;

import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.service.CardService;
import com.zfkj.demo.vo.basevo.Result;

import com.zfkj.demo.vo.reqvo.card.SaveCardIntroReVo;
import com.zfkj.demo.vo.respvo.card.CardRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "MINI -名片管理-首页")
@RestController
@RequestMapping("/api/card")
public class IntroductController {
    @Autowired
    CardService cardService;
    @ApiOperation(value = "获取员工简介", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/getCard")
    public Result<List<CardRespVo>> getCard(@RequestParam("cardId") int id){
        return Result.success(cardService.returnCard(id));
    }

    @ApiOperation(value = "简介管理", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @PostMapping("/saveCardIntro")
    public Result<Boolean> saveCardIntro(@RequestBody SaveCardIntroReVo reVo){
        return Result.success(cardService.saveIntroduct(reVo));
    }

    @ApiOperation(value = "返回员工简介", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.YUANMIAOMIAO)
    @GetMapping("/returnCard")
    public Result<List<CardRespVo>> returnCard(){
        return Result.success(cardService.returnCardBy());
    }
}
