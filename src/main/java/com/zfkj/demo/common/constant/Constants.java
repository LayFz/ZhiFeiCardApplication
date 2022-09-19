package com.zfkj.demo.common.constant;

/**
 * 通用常量信息
 *
 * @author lijunlin
 */
public class Constants {

    /**
     * 登录 redis key
     */
    public static final String LOGIN_CODE_KEY = "login_user:";
    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";
    /**
     * 令牌自定义标识
     */
    public static final String AUTH_HEADER = "Authorization";
    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 5*60;

    /**
     * 令牌有效期（分钟）
     */
    public static final Integer TOKEN_EXPIRE = 4320*60;

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    public static final String OP_SUCCESS = "操作成功";

    public static final String OP_ERROR = "操作失败";

    /**
     * 文件下载
     */
    public static final String STR_DOWNLOAD = "download";
    public static final String STR_UPLOAD_IMG = "upload/img";
    public static final String STR_UPLOAD_FILE = "upload/file";
    public static final String STR_UPLOAD_VIDEO = "upload/video";

}
