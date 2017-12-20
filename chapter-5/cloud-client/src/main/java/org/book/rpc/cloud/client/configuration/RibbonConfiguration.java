package org.book.rpc.cloud.client.configuration;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfiguration {

    /**
     * 配置Ribbon的负载均衡策略
     * @return
     */
    @Bean
    public IRule ribbonRule() {
        return new BestAvailableRule();
    }

}
