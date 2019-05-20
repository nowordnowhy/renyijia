package com.renyija.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-05-15
 * @email : zhou_wenya@163.com
 */
@Slf4j
public class R extends HashMap implements Serializable {

    private static final long serialVersionUID = 4973550117529318010L;

    public R() {
        put("code", HttpStatus.SC_OK);
    }

    public static R error() {
        log.info("error result==== 未知异常，请联系管理员");
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static R error(String message) {
        log.info("error message result====" + message);
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, message);
    }

    public static R error(int code, String message) {
        R r = new R();
        r.put("code", code);
        r.put("message", message);
        log.info("error result====" + message);
        return r;
    }


    public static R ok(String message) {
        R r = new R();
        r.put("message", message);
        log.info("ok string result====" + message);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }



}
