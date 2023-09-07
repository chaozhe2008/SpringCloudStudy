package com.test.controller;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class PublishController {
    @Resource
    StreamBridge bridge; //通过bridge来发送消息
    @RequestMapping("/publish")
    public String publish(){
    //第一个参数其实就是RabbitMQ的交换机名称(数据会发送给这个交换机，到达哪个消息队列，不由我 //这个交换机的命名稍微有一些规则:
    //输入: <名称> + -in- + <index>
    //输出: <名称> + -out- + <index> //这里我们使用输出的方式，来将数据发送到消息队列，注意这里的名称会和之后的消费者Bean名称进行
        bridge.send("test-out-0", "HelloWorld!");
        return "Success!"+new Date(); }
}
