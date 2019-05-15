package com.renyijia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-05-14
 * @email : zhou_wenya@163.com
 */
@EnableEurekaClient
@SpringBootApplication
public class UserServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class,args);
    }
}