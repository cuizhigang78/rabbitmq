package com.cuizg.springboot;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConsumerReceiver
 * @Author Maxwell
 * @Date 2021/9/25 23:12
 * @Description ConsumerReceiver
 * @Version 1.0
 */
@Component
public class ConsumerReceiver {

    @RabbitListener(queues = "work_sb_mq_q")
    private void m(String message) {
        System.out.println(message);
    }
}
