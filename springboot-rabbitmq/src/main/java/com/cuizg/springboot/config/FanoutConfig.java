package com.cuizg.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FanoutConfig
 * @Author Maxwell
 * @Date 2021/9/25 23:13
 * @Description FanoutConfig
 * @Version 1.0
 *
 *
 * Fanout模式需要声明exchange，并绑定queue
 */
@Configuration
public class FanoutConfig {

    @Bean
    public Queue fanoutQ1() {
        return new Queue("fanout.q1");
    }

    @Bean
    public Queue fanoutQ2() {
        return new Queue("fanout.q2");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Binding bindQ1() {
        return BindingBuilder.bind(fanoutQ1()).to(fanoutExchange());
    }

    @Bean
    public Binding bindQ2() {
        return BindingBuilder.bind(fanoutQ2()).to(fanoutExchange());
    }
}
