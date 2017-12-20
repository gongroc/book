package org.book.rpc.dubbo.gateway.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.book.rpc.dubbo.service.IHello;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Reference
    private IHello hello;

    @RequestMapping(value = "/")
    public String say() {
        return hello.say("rpc");
    }

}
