package com.zfkj.demo.controller.admin;


import com.zfkj.demo.common.constant.ApiTextHelperConstant;
import com.zfkj.demo.common.constant.DeveloperConstant;
import com.zfkj.demo.common.exception.BusinessRootRuntimeException;
import com.zfkj.demo.common.exception.Exceptions;
import com.zfkj.demo.vo.basevo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 接口错误 控制器
 * </p>
 *
 * @author lijunlin
 * @date  2022-1-10
 */
@Api(tags = "后台管理系统-后台管理通用错误消息接口-错误接口通用规则")
@RestController
public class ErrorController {

    @ApiOperation(value = "错误接口通用规则", notes = ApiTextHelperConstant.DEVELOPER + DeveloperConstant.lijunlin)
    @GetMapping("/error")
    public Result<String> error() {
        throw new BusinessRootRuntimeException(Exceptions.ApiEX.API_LOST);
    }

}
