package org.book.rpc.dubbo.client;

import org.book.rpc.dubbo.client.service.imple.InvokeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DubboClientApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DubboClientApplication.class, args);
		InvokeService service = run.getBean(InvokeService.class);
		System.out.println("收到返回结果："+service.hello.say("rpc"));
	}
}
