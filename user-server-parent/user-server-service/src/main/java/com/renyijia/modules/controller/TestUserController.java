package com.renyijia.modules.controller;

import com.renyijia.common.utils.BaseResult;
import com.renyijia.feigenclient.InformationServerFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-05-16
 * @email : zhou_wenya@163.com
 */
@RestController
@RequestMapping("test")
public class TestUserController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private InformationServerFeignClient informationServerFeignClient;


    @GetMapping("test1")
    public com.renyijia.fallback.BaseResult<String> test1() {
        return informationServerFeignClient.helloInformation();
    }

    @GetMapping("test2")
    public BaseResult<String> test2() {
        return new BaseResult<>(informationServerFeignClient.information());
    }




    @GetMapping("helloUser")
    public BaseResult<String> helloUser() {
//        try {
//            Thread.sleep(1000000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        logger.info("message is hello-user-server {}", System.currentTimeMillis());
        BaseResult baseResult = new BaseResult<>("hello-user-server");
        return baseResult;
    }

}
