package org.bymarx.account.util;

/**
 * SpringMVC JSON 返回数据
 *
 * @author howq
 * @create 2017/3/10 14:17
 **/

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Result<T> implements Serializable {
    public static final Result SUCCESS;
    private Code code;
    private String message;
    private T data;

    public static boolean isSuccessResult(Result r) {
        return r == null?false:r.isSuccess();
    }

    public Result() {
        this.code = Code.SUCCESS;
    }

    public Result(Code code, String message, T data) {
        this.code = Code.SUCCESS;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(T data) {
        this.code = Code.SUCCESS;
        this.code = Code.SUCCESS;
        this.data = data;
    }

    public static <T> Result<T> newSuccess(T data) {
        return new Result(Code.SUCCESS, "", data);
    }

    public static <T> Result<T> newFailed(String message) {
        return new Result(Code.ERROR, message, (Object)null);
    }

    public static <T> Result<T> newFailed(String message, T data) {
        return new Result(Code.ERROR, message, data);
    }

    @JsonIgnore
    @JSONField(
            serialize = false
    )
    public boolean isSuccess() {
        return Code.SUCCESS.equals(this.code);
    }

    public Code getCode() {
        return this.code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void doErrorHandle(String message) {
        this.code = Code.ERROR;
        this.message = message;
    }

    static {
        SUCCESS = new Result(Code.SUCCESS, (String)null, (Object)null);
    }

    public static enum Code {
        SUCCESS,
        ERROR;

        private Code() {
        }
    }
}
