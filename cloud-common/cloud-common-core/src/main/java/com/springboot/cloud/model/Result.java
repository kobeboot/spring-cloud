package com.springboot.cloud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.cloud.exception.BaseException;
import com.springboot.cloud.exception.ErrorType;
import com.springboot.cloud.exception.SystemErrorType;
import lombok.Data;

import java.time.Instant;
import java.time.ZonedDateTime;

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
    private Instant time;

    /**
     * 处理结果响应数据
     */
    private T data;

    public static final String SUCCESS_CODE = "200";

    public static final String SUCCESS_MESG = "处理成功";

    public Result() {
        this.time = ZonedDateTime.now().toInstant();
    }

    public Result(ErrorType errorType) {
        this.code = errorType.getCode();
        this.mesg = errorType.getMesg();
        this.time = ZonedDateTime.now().toInstant();
    }

    public Result(ErrorType errorType, T data) {
        this(errorType);
        this.data = data;
    }

    public Result(String code, String mesg, T data) {
        this.code = code;
        this.mesg = mesg;
        this.data = data;
        this.time = ZonedDateTime.now().toInstant();
    }

    public static Result<?> success(Object data) {
        return new Result<>(SUCCESS_CODE, SUCCESS_MESG, data);
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

    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESS_CODE.equals(this.code);
    }

    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }

}
