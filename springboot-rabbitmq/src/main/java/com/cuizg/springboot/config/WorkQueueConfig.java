package com.cuizg.springboot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName WorkQueueConfig
 * @Author Maxwell
 * @Date 2021/9/25 23:17
 * @Description WorkQueueConfig
 * @Version 1.0
 */
@Configuration
public class WorkQueueConfig {

    @Bean
    public Queue getQueue() {
        return new Queue("queue-sms");
    }
}
