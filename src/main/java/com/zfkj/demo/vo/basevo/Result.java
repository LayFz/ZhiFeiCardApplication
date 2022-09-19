package com.zfkj.demo.vo.basevo;

import com.zfkj.demo.common.constant.Constants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.net.HttpURLConnection;

/**
 * @author lijunlin
 * 定义统一的Result返回对象,所有接口返回统一使用此对象
 */
@Data
public class Result<T> {

    @ApiModelProperty(value = "是否成功")
    private boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "消息")
    private String msg;

    @ApiModelProperty(value = "返回数据")
    private T data;

    private Result() {
    }

    public static Result ok() {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(ResultCode.OK);
        r.setMsg("成功");
        return r;
    }

    public static Result error() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMsg("失败");
        return r;
    }

    public Result data(T data) {
        this.setData(data);
        return this;
    }


    public Result message(String message) {
        this.setMsg(message);
        return this;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    // ***********************************************************************************************************
    public Result(T data, int code, String msg, boolean success) {
        this.success = success;
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public static <T> Result<T> bool(boolean b) {
        return b ? success() : failed();
    }

    public static <T> Result<T> success() {
        return new Result<T>(null, HttpURLConnection.HTTP_OK, Constants.OP_SUCCESS, true);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(data, HttpURLConnection.HTTP_OK, Constants.OP_SUCCESS, true);
    }

    public static <T> Result<T> success(T data, String msg) {
        return new Result<T>(data, HttpURLConnection.HTTP_OK, msg, true);
    }

    public static <T> Result<T> success(T data, Integer code) {
        return new Result<T>(data, code, Constants.OP_SUCCESS, true);
    }

    public static <T> Result<T> success(T data, Integer code, String msg) {
        return new Result<T>(data, code, msg, true);
    }

    public static <T> Result<T> failed() {
        return new Result<T>(null, HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.OP_ERROR, false);
    }

    public static <T> Result<T> failed(T data) {
        return new Result<T>(data, HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.OP_ERROR, false);
    }

    public static <T> Result<T> failed(T data, String msg) {
        return new Result<T>(data, HttpURLConnection.HTTP_INTERNAL_ERROR, msg, false);
    }

    public static <T> Result<T> failed(Integer code, String msg) {
        return new Result<T>(null, code, msg, false);
    }
    // *************************************************************************************************************
}
