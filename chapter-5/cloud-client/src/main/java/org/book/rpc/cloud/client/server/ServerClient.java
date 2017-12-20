package org.book.rpc.cloud.client.server;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "SERVER")
public interface ServerClient {
    @RequestMapping(value = "hello")
    String hello(@RequestParam("param") String param);
}
