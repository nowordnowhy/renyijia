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
        put("code", 0);
    }

    public static R error() {
        logger.info("");
        logger.info("error result==== 未知异常，请联系管理员");
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        logger.info("error msg result====" + msg);
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        logger.info("error result====" + msg);
        return r;
    }

    /**
     * 微信發送模板消息
     */
    public static R error(String code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        logger.info("error result====" + msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        logger.info("ok string result====" + msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
//        logger.info("ok map result====" + JSO.toJSONString(map));
        return r;
    }

    public static R ok() {
        return new R();
    }

    public static R ok(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        logger.info("ok result====" + msg);
        return r;
    }


}
