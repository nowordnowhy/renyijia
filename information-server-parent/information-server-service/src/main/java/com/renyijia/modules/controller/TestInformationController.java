package com.renyijia.modules.controller;

import com.renyijia.common.utils.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-05-16
 * @email : zhou_wenya@163.com
 */
@RestController
@RequestMapping("test")
public class TestInformationController {

    Logger logger = LoggerFactory.getLogger(getClass());



    @GetMapping("helloInformation")
    public BaseResult<String> helloInformation() {
        int rad=new Random().nextInt(3000);
        System.out.println("rad = " + rad);
        try {
            Thread.sleep(rad);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("message is hello_information is {}", System.currentTimeMillis());
        return new BaseResult<>("hello_information");
    }


}
