package org.book.rpc.dubbo.server.service.imple;


import com.alibaba.dubbo.config.annotation.Service;
import org.book.rpc.dubbo.service.IHello;

@Service
public class HelloImple implements IHello {

    @Override
    public String say(String msg) {
        System.out.println("你好：" + msg);
        return msg;
    }

}
