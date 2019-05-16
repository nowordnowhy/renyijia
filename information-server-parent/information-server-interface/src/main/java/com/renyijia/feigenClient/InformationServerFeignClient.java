package com.renyijia.feigenClient;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-05-16
 * @email : zhou_wenya@163.com
 */
@FeignClient("information-server")
public interface InformationServerFeignClient {
}
