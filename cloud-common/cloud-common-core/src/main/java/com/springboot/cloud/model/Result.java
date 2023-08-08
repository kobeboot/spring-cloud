package com.springboot.cloud.model;

import com.springboot.cloud.exception.BaseException;
import com.springboot.cloud.exception.ErrorType;
import com.springboot.cloud.exception.SystemErrorType;
import lombok.Data;

import java.text.SimpleDateFormat;

@Data
public class Result<T> {

    /**
     * 处理结果code
     */
    private String code;

    /**
     * 处理结果mesg
     */
    private String mesg;

    /**
     * 处理时间time
     */
    private String time;

    /**
     * 处理结果响应数据
     */
    private T data;

    public Result() {
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(System.currentTimeMillis());
    }

    public Result(ErrorType errorType) {
        this.code = errorType.getCode();
        this.mesg = errorType.getMesg();
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(System.currentTimeMillis());
    }

    public Result(ErrorType errorType, T data) {
        this(errorType);
        this.data = data;
    }

    public Result(String code, String mesg, T data) {
        this.code = code;
        this.mesg = mesg;
        this.data = data;
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(System.currentTimeMillis());
    }

    public static Result<?> success(Object data) {
        return new Result<>("200", "success", data);
    }

    public static Result<?> success() {
        return success(null);
    }

    public static Result<?> fail() {
        return new Result<>(SystemErrorType.SYSTEM_ERROR);
    }

    public static Result<?> fail(Object data) {
        return new Result<>(SystemErrorType.SYSTEM_ERROR, data);
    }

    public static Result<?> fail(BaseException exception) {
        return new Result<>(exception.getErrorType(), null);
    }

    public static Result<?> fail(BaseException exception, Object data) {
        return new Result<>(exception.getErrorType(), data);
    }

    public static Result<?> fail(ErrorType errorType) {
        return new Result<>(errorType, null);
    }

    public static Result<?> fail(ErrorType errorType, Object data) {
        return new Result<>(errorType, data);
    }

}
