package com.cuizg.springboot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName HelloWorldConfig
 * @Author Maxwell
 * @Date 2021/9/25 23:14
 * @Description HelloWorldConfig
 * @Version 1.0
 */
@Configuration
public class HelloWorldConfig {

    @Bean
    public Queue setQueue() {
        return new Queue("queue-hello-world");
    }
}
