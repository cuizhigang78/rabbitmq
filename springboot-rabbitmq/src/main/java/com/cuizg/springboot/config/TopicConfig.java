package com.cuizg.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName TopicConfig
 * @Author Maxwell
 * @Date 2021/9/25 23:14
 * @Description TopicConfig
 * @Version 1.0
 */
@Configuration
public class TopicConfig {

    @Bean
    public Queue topicQ1() {
        return new Queue("topic.q1");
    }

    @Bean
    public Queue topicQ2() {
        return new Queue("topic.q1");
    }

    @Bean
    public DirectExchange topicExchange() {
        return new DirectExchange("topicExchange");
    }

    @Bean
    public Binding bindQ1() {
        return BindingBuilder.bind(topicQ1()).to(topicExchange()).with("changsha.*");
    }

    @Bean
    public Binding bindQ2() {
        return BindingBuilder.bind(topicQ1()).to(topicExchange()).with("#.beijing");
    }
}
