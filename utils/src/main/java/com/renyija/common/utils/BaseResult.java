package com.renyija.common.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

import java.io.Serializable;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-05-15
 * @email : zhou_wenya@163.com
 */
@Data
@Slf4j
public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = -5356718467240233205L;
    private static final int BUSINESS_ERROR = 1;
    private static final int TOKEN_ERROR = 1001;
    private int code;
    private T data;
    private String message;
    private Exception error;

    public BaseResult() {
        this.code = HttpStatus.SC_OK;
    }

    public static <T> BaseResult<T> getBaseResult(T data) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(HttpStatus.SC_OK);
        baseResult.setData(data);
        return baseResult;

    }

    public static <T> BaseResult<T> getBaseResult(int code, T data, String message) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(HttpStatus.SC_OK);
        baseResult.setData(data);
        baseResult.setMessage(message);
        return baseResult;
    }

    public static <T> BaseResult<T> getBaseResult(int code, String message) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(HttpStatus.SC_OK);
        baseResult.setMessage(message);
        return baseResult;
    }

    public static <T> BaseResult<T> getMessage(String message) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(BUSINESS_ERROR);
        baseResult.setMessage(message);
        return baseResult;
    }

    public static <T> BaseResult<T> tokenError() {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(TOKEN_ERROR);
        baseResult.setMessage("token error");
        return baseResult;
    }

    public static <T> BaseResult<T> getError(Exception error) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        baseResult.setError(error);
        baseResult.setMessage("未知异常，请联系管理员");
        return baseResult;

    }

}
