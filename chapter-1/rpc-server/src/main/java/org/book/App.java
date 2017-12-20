package org.book;

import org.book.service.IHello;
import org.book.service.imple.HelloImple;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        // 注册服务
        server.register(IHello.class, HelloImple.class);
        // 启动并绑定端口
        server.start(8020);
    }

}
