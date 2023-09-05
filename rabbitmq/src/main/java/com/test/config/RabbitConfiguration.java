package com.test.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean("directDlExchange")
    public Exchange dlExchange(){
    //创建一个新的死信交换机
        return ExchangeBuilder.directExchange("dlx.direct").build();
    }

    @Bean("dl-queue") //创建一个新的死信队列
    public Queue dlQueue(){
        return QueueBuilder
                .nonDurable("dl-queue")
                .deadLetterExchange("dlx.direct") //指定死信交换机
                .deadLetterRoutingKey("dl-key") //指定死信RoutingKey
                .build();
}
    @Bean("dlBinding") //死信交换机和死信队列进绑定
    public Binding dlBinding(@Qualifier("directDlExchange") Exchange exchange,
                           @Qualifier("dl-queue") Queue queue){
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("dl-key")
                .noargs();
    }

    @Bean("dl-queue")
    public Queue queue(){
            return QueueBuilder
            .nonDurable("dl-queue").build();
            }


    @Bean("directExchange") //定义交换机Bean，可以很多个
    public Exchange exchange(){
          return ExchangeBuilder.directExchange("amq.direct").build();
    }

//    @Bean("q1-bean") //定义消息队列
//    public Queue queue(){
//    return QueueBuilder
//            .nonDurable("q1") //非持久化类型
//            .build();
//    }

//    @Bean("binding")
//    public Binding binding(@Qualifier("directExchange") Exchange exchange,
//                           @Qualifier("q1-bean") Queue queue){ //将我们刚刚定义的交换机和队列进行绑定
//            return BindingBuilder.bind(queue) //绑定队列
//            .to(exchange) //到交换机
//            .with("q1-key") //使用自定义的routingKey
//            .noargs();
//            }
}
