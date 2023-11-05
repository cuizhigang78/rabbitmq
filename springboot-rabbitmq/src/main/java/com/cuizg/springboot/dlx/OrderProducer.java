package com.cuizg.springboot.dlx;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
 
@RestController
public class OrderProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
 
    /**
     * 订单交换机
     */
    @Value("${rk.order.exchange}")
    private String orderExchange;
    /**
     * 订单路由key
     */
    @Value("${rk.order.routingKey}")
    private String orderRoutingKey;
 
    @RequestMapping("/sendOrder")
    public String sendOrder() {
        String msg = "rk 学 rabbitmq";
        //发送消息  参数一:交换机 参数二:路由键(用来指定发送到哪个队列)
        rabbitTemplate.convertAndSend(orderExchange, orderRoutingKey, msg, message -> {
            // 设置消息过期时间 10秒过期    如果过期时间内还没有被消费 就会发送给死信队列
            message.getMessageProperties().setExpiration("10000");
            return message;
        });
        return "success";
    }
 
}