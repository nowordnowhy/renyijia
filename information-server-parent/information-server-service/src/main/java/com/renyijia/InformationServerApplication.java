package com.renyijia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-05-15
 * @email : zhou_wenya@163.com
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class InformationServerApplication {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(InformationServerApplication.class, args);
    }
}
