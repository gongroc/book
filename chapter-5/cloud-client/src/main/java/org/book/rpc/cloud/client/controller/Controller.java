package org.book.rpc.cloud.client.controller;

import org.book.rpc.cloud.client.server.ServerClient;
import org.book.rpc.cloud.client.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {


    @Autowired
    private ServerClient serverClient;

    /**
     * 使用fegin调用远程服务
     *
     * @return
     */
    @RequestMapping(value = "feign")
    public String sayFeign() {
        return serverClient.hello("cloud");
    }

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 使用rabbion调用远程服务
     *
     * @return
     */
    @RequestMapping(value = "ribbon")
    public String sayRabbion() {
        return restTemplate.getForObject("http://SERVER/hello?param=cloud", String.class);
    }

    @Autowired
    private BusinessService businessService;

    /**
     * 使用raibbon调用远程服务并触发熔断机制
     *
     * @return
     */
    @RequestMapping(value = "ribbon/hystrix")
    public String sayRabbionHystrix() {
        return businessService.sayRabbionHystrix();
    }

    /**
     * 使用feign调用远程服务并触发熔断机制
     *
     * @return
     */
    @RequestMapping(value = "feign/hystrix")
    public String sayFeignHystrix() {
        return businessService.sayFeignHystrix();
    }


    @Value("${config}")
    private String config;

    /**
     * 获取统一配置中心中配置文件的参数
     *
     * @return
     */
    @RequestMapping(value = "config")
    public String config() {
        return config;
    }

}
