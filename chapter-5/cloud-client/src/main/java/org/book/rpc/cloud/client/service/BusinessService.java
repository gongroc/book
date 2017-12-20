package org.book.rpc.cloud.client.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.book.rpc.cloud.client.server.ServerClientHystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Component
public class BusinessService {


    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public String sayRabbionHystrix() {
        return restTemplate.getForObject("http://SERVER/fail", String.class);
    }

    public String fallback() {
        return "容错数据";
    }

    @Resource
    private ServerClientHystrix serverClientHystrix;

    public String sayFeignHystrix() {
        return serverClientHystrix.fail();
    }

}
