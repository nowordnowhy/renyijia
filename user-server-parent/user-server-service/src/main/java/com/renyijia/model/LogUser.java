package com.renyijia.model;

import lombok.Data;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-06-16
 * @email : zhou_wenya@163.com
 */
@Data
public class LogUser {
    private Integer userId;
    private String userName;
    private String password;
    private String oldPassword;
}

