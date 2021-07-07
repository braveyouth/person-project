package com.example.consumer;

import com.example.domain.GrowthPlanInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangy
 * @Time 2020-12-11 20:23
 */
@Component
@RabbitListener(queues = "hello")
public class TaskConsumer {

    private static final Logger logger = LoggerFactory.getLogger(TaskConsumer.class);

    @RabbitHandler
    public void hello(String text){
        logger.info("开始消费消息..."+System.currentTimeMillis());
        logger.info("消费消息: "+text);
        logger.info("结束消费消息..."+System.currentTimeMillis());
    }



}
