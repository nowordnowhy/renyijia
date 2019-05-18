package com.renyijia.common.utils;

import lombok.Data;
import org.apache.http.HttpStatus;

import java.io.Serializable;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-05-15
 * @email : zhou_wenya@163.com
 */
@Data
public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = -5356718467240233205L;
    private int code;
    private T data;
    private String message;
    private Exception error;

    public BaseResult(T data) {
        this.data = data;
        this.code = HttpStatus.SC_OK;
    }

    public BaseResult(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(Exception error) {
        this.code = HttpStatus.SC_INTERNAL_SERVER_ERROR;
        this.error = error;
        this.message = "未知异常，请联系管理员";
    }

}
