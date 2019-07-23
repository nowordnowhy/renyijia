package com.renyija.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.ObjectUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Token {

    private static final String EBCRYPTION = "renyijia";

    /**
     * 加密
     *
     * @param id
     * @return
     */
    public static String createJWT(String id, String userName) {

        SignatureAlgorithm signatureAlgorithm = io.jsonwebtoken.SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();

        Date now = new Date(nowMillis);
        Map<String, Object> m=new HashMap<>();
        m.put("userName",userName);
        m.put("userId",id);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(EBCRYPTION);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setClaims(m)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }


    /**
     * 解密
     *
     * @param jwt
     */
    public static Map<String, Object> parseJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(EBCRYPTION))
                .parseClaimsJws(jwt).getBody();

        Map<String, Object> map = new HashMap<>();
        map.put("times", claims.getIssuedAt());
        map.put("userId", claims.get("userId"));
        map.put("userName", claims.get("userName"));
        return map;
    }

    /**
     * token 校验
     *
     * @param map
     * @return
     */
    public static Boolean tokenCheck(Map<String, Object> map) {
        if (map != null && ObjectUtils.allNotNull(
                map.get("userId"), map.get("times"),map.get("userName"))) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {

        Token token = new Token();
        String jwt = createJWT("1", "17610860118");
        System.out.println(jwt);
//        String sss="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3IiwiYXBwSWQiOiIiLCJwaG9uZSI6IjE3NjEwODYwMTE4IiwiaWF0IjoxNTQ1MDE3NTQ5fQ.hData/realTimeData/optimum?deviceId=1&token=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3IiwiYXBwSWQiOiIiLCJwaG9uZSI6IjE3NjEwODYwMTE4IiwiaWF0IjoxNTQ1MDE3NTQ5fQ.kKBMFVLCCLX2KQpYNVwqEd6MNnLW03gvZUEKBUcQRnQ";
//String sss="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzMzMzIiwiYXBwSWQiOiJ4eHh4eHh4eHh4eHh4eHh4IiwicGhvbmUiOiIxNzYwMDMzNTQ1NSIsImlhdCI6MTU0NTAxODI5OH0.XLIbfQZhn0iyDv7v6e4sqrSZzbxQ-NEjgz9d08xDR20\n";
//        String ttt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiYXBwSWQiOiJmZGY1MWQ1MjFkY2Y0Mjk0YjA3YWQ2YmMwYjgxNjRiYSIsInBob25lIjoiMTc2MTA4NjAxMTgiLCJpYXQiOjE1NDgwNTc0ODB9.nShrukOOTFm_yEvbDe9qB9wEP-8zmazw0FrLs_iv_Ic";
        Map<String, Object> stringObjectMap = parseJWT(jwt); //token.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3IiwiYXBwSWQiOiI0MWYzZWNkNGQzNDc0ZjRlOTFjOWMyNmExMGM4OGZiNCIsImlhdCI6MTU0MjAyNjAwOH0.AgRFRwZJ7_Uk1s9M9mJrUGmPhNSqCJxg05cBIR3LWxE");
        System.out.println(stringObjectMap);
       Boolean b= tokenCheck(stringObjectMap);
        System.out.println("b = " + b);
    }
}
