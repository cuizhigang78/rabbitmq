package com.cuizg.springboot.dlx;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
 
/**
 * 订单消费者
 */
@Component
public class OrderConsumer {
 
    /**
     * 监听队列回调的方法
     *
     * @param msg
     */
    @RabbitListener(queues = "rk_order_queue")
    public void orderConsumer(String msg) {
        System.out.printf(">>正常订单消费者消息MSG:{%s}<<", msg);
    }
}