package org.book.rpc.cloud.client.server.hystrix;


import org.book.rpc.cloud.client.server.ServerClientHystrix;
import org.springframework.stereotype.Component;

@Component
public class ServerClientHystrixImple implements ServerClientHystrix {
    @Override
    public String fail() {
        return "熔断数据";
    }
}
