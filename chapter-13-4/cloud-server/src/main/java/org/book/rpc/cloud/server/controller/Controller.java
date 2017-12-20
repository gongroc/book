package org.book.rpc.cloud.server.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping(value = "/private/{id}")
    public String api(@PathVariable String id) {
        return "success : " + id;
    }

}
