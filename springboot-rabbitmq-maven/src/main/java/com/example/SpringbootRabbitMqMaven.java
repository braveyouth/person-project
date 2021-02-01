package com.example;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author zhangy
 * @Time 2020-12-09 22:33
 */
@SpringBootApplication
public class SpringbootRabbitMqMaven {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitMqMaven.class,args);
    }

    @Bean
    public Jackson2JsonMessageConverter customConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
