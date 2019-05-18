package com.renyijia.common.utils;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-05-15
 * @email : zhou_wenya@163.com
 */
public class R extends HashMap implements Serializable {

    private static final long serialVersionUID = 4973550117529318010L;
    private static Logger logger = LoggerFactory.getLogger(R.class);

    public R() {
        put("code", HttpStatus.SC_OK);
    }

    public static R error() {
        logger.info("error result==== 未知异常，请联系管理员");
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static R error(String message) {
        logger.info("error message result====" + message);
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, message);
    }

    public static R error(int code, String message) {
        R r = new R();
        r.put("code", code);
        r.put("message", message);
        logger.info("error result====" + message);
        return r;
    }


    public static R ok(String message) {
        R r = new R();
        r.put("message", message);
        logger.info("ok string result====" + message);
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
