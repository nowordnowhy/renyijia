package com.renyijia.fallback;

import com.renyijia.feigenclient.InformationServerFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-05-16
 * @email : zhou_wenya@163.com
 */
@Component
public class InformationServerFallback implements InformationServerFeignClient {
    @Override
    public String information() {
        return "fuck you information";
    }

    @Override
    public BaseResult<String> helloInformation() {
        return new BaseResult<>("tttttt ----");
    }
}
