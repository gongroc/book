package org.book.mq.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MQReceiver {

    @RabbitListener(queues = "mq")
    public void process(String message) {
        System.out.println("Message is : " + message);
    }

}
