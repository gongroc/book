package org.book.rpc.cloud.client.server;

import org.book.rpc.cloud.client.server.hystrix.ServerClientHystrixImple;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "SERVER", fallback = ServerClientHystrixImple.class)
public interface ServerClientHystrix {

    @RequestMapping("fail")
    String fail();
}
