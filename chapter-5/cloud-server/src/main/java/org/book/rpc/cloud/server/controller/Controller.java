package org.book.rpc.cloud.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping(value = "hello")
    public String hello(@RequestParam("param") String param) {
        return "rpc：" + param;
    }

    @RequestMapping(value = "fail")
    public String helloBreak() {
        throw new RuntimeException("模拟出错");
    }

}
