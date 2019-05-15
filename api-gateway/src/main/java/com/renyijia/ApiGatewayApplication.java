package com.renyijia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-05-15
 * @email : zhou_wenya@163.com
 */
@EnableZuulProxy
@SpringBootApplication
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class,args);
    }
}
