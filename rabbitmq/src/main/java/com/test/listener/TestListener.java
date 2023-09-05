package com.test.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

//@Component //注册为Bean
public class TestListener {
//    @RabbitListener(queues = "q1") //定义此方法为队列q1的监听器
//    public void test(Message message){
//        System.out.println(new String(message.getBody()));
//    }

    @RabbitListener(queues = "q1")
    public String receiver(String data){
        System.out.println("q1 listener:  "+data);
        return "received!"; }
}
