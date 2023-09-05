package com.test.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DLListener {
    @RabbitListener(queues = "dl-queue")
            public void receiver(Message message){
        System.out.println(message);
    }
}
