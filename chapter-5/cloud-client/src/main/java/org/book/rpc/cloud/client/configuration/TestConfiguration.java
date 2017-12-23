package org.book.rpc.cloud.client.configuration;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClient(name = "SERVER", configuration = RibbonConfiguration.class)
public class TestConfiguration {
}
