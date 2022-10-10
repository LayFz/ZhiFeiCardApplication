package com.zfkj.demo.controller.publicuser;

import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.dao.entity.CardDate;
import com.zfkj.demo.service.CardDateService;
import com.zfkj.demo.vo.basevo.Result;
import com.zfkj.demo.vo.reqvo.user.LoginUserInfoVO;
import com.zfkj.demo.vo.respvo.user.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liujie
 * @version 1.0
 * @date 2022/10/10 13:58
 */
@Api(tags = "MINI小程序-统计模块-首页")
@RestController
@RequestMapping("/api/user/count")
public class CountMiniController {

    @Autowired
    CardDateService cardDateService;


    @ApiOperation(value = "保存名片", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/saveCard")
    public Result<Boolean> saveCard(@RequestParam("cardId") int card_id) {
        return Result.success(cardDateService.saveCard(card_id));
    }

    @ApiOperation(value = "添加联系人", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.LIUJIE)
    @GetMapping("/addPhone")
    public Result<Boolean> addPhone(@RequestParam("cardId") int card_id) {
        return Result.success(cardDateService.addPhone(card_id));
    }
}
