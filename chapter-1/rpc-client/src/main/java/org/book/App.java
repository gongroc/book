package org.book;

import org.book.service.IHello;

import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) {
        IHello service = Client.get(IHello.class, new InetSocketAddress("localhost", 8020));
        System.out.println(service.say("RPC"));
    }
}
