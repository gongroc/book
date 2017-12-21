package org.book.task.quartz.service;

import org.springframework.stereotype.Component;

@Component
public class DemoService {

    public void server(String msg){
        System.out.println(msg);
    }
}
