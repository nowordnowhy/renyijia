package com.renyijia.jwt;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-05-23
 * @email : zhou_wenya@163.com
 */
public enum  Role implements GrantedAuthority {
    ROLE_ADMIN, ROLE_CLIENT;
    @Override
    public String getAuthority() {
        return name();
    }
}
