package com.zfkj.demo.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program:
 * @description: 异常处理
 * @author: lijunlin
 * @create: 2019/12/30 16:29
 **/
public interface Exceptions extends ExceptionType {
    /**
     * 注：所有定义为401的code代表前端需重回登录页面
     */

    /**
     * 公共异常
     */
    @Getter
    @AllArgsConstructor
    enum Common implements Exceptions {
        // Feign 服务异常
        FEIGN_INVOKE_ERROR(500, "Feign 服务调用异常"),
        DATA_IS_NULL(500, "数据为空"),
        NOT_PERMISSION(401, "无权访问!"),
        REQUSET_TYPE_ERROR(500, "访问类型错误!"),
        DATA_IS_ERROR(500, "数据错误!"),
        PARAMTER_IS_ERROR(500, "参数不正确！"),
        ;
        private int code;
        private String description;
    }

    @Getter
    @AllArgsConstructor
    enum UserEX implements Exceptions {
        USER_HAVE(500, "该账号或手机号已存在"),
        USER_CLOSE(500, "当前账号已被禁用,请联系管理员"),
        NOT_PREMISS(401, "无访问权限"),
        NO_ROLE(500, "该用户无角色"),
        ACCOUNT_ERROR(500, "用户名或密码错误"),
        PHONE_ERROR(500, "手机号或密码错误"),
        ACCOUNT_NOT_HAVE(500, "登录账号不存在,请重新登录"),
        ACCOUNT_NOT_FIND(500, "未查询到该用户信息"),
        ;
        private int code;
        private String description;
    }

    @Getter
    @AllArgsConstructor
    enum FileUpload implements Exceptions {
        FILE_TYPE_ERROR(500, "图片格式不正确"),
        UPLOAD_SUFFIX_ERROR(500,"不支持的文件后缀"),
        ;
        private int code;
        private String description;
    }
    @Getter
    @AllArgsConstructor
    enum LoginEX implements Exceptions {
        CAPTCHA_EXPIRE(500, "验证码已失效"),
        CAPTCHA_ERROR(500, "验证码错误或已失效"),
        USER_ERROR(500, "账号或密码错误"),
        USER_STATUS_ERROR(500, "用户被禁用"),
        GET_USER_ERROR(500, "获取登录用户信息异常"),
        NO_LOGIN(500, "您还未登录，请登录后操作"),
        ;
        private int code;
        private String description;
    }
    @Getter
    @AllArgsConstructor
    enum ApiEX implements Exceptions {
        API_LOST(404, "请求失败，接口不存在或请求方式错误"),
        NO_AUTH(401, "您还没有该操作的权限,请联系管理员分配"),
        ;
        private int code;
        private String description;
    }
    @Getter
    @AllArgsConstructor
    enum DictEX implements Exceptions {
        SEALED(500, "该数据字典不允许编辑"),
        ;
        private int code;
        private String description;
    }
    @Getter
    @AllArgsConstructor
    enum MenuEX implements Exceptions {
        ROUTER_KEY_REPEAT(500, "路由标识重复"),
        DELETE_AUTH_ERROR(500, "路由已绑定角色信息，不可删除"),
        ;
        private int code;
        private String description;
    }
}
