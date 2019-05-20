package com.renyijia.feigenclient;

import com.renyija.common.utils.BaseResult;
import com.renyijia.fallback.InformationServerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-05-16
 * @email : zhou_wenya@163.com
 */
@FeignClient(value = "information-server",fallback = InformationServerFallback.class)
@Component
public interface InformationServerFeignClient {
    /**
     * @return
     */
    @RequestMapping("if/information")
    String information();

    @GetMapping("test/helloInformation")
    BaseResult<String> helloInformation() ;
}
