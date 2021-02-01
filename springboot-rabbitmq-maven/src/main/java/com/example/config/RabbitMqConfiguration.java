package com.example.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangy
 * @Time 2020-12-11 20:17
 */
@Configuration
public class RabbitMqConfiguration {

    @Bean
    public Queue  helloQueue(){
        return new Queue("hello",true);
    }

    @Bean
    public Queue growthPlanQueue(){
        return new Queue("growthplan",true);
    }
}
