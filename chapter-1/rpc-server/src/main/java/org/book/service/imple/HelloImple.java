package org.book.service.imple;

import org.book.service.IHello;

public class HelloImple implements IHello {

    public String say(String msg) {
        System.out.println("收到消息：" + msg);
        return "你好：" + msg;
    }

}
