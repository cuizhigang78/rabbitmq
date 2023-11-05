package com.cuizg.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName DirectConfig
 * @Author Maxwell
 * @Date 2021/9/25 23:13
 * @Description DirectConfig
 * @Version 1.0
 */
@Configuration
public class DirectConfig {

    @Bean
    public Queue directQ1() {
        return new Queue("direct.q1");
    }

    @Bean
    public Queue directQ2() {
        return new Queue("direct.q1");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    public Binding bindQ1() {
        return BindingBuilder.bind(directQ1()).to(directExchange()).with("china.changsha");
    }

    @Bean
    public Binding bindQ2() {
        return BindingBuilder.bind(directQ1()).to(directExchange()).with("china.beijing");
    }
}
