package com.cuizg.direct;

import com.cuizg.constants.RabbitConstant;
import com.cuizg.utils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @ClassName CNWeather
 * @Author Maxwell
 * @Date 2021/9/24 23:27
 * @Description CNWeather
 * @Version 1.0
 */
public class CNWeather {

    public static void main(String[] args) throws IOException {
        Connection conn = RabbitUtils.getConnection();
        Channel channel = conn.createChannel();
        channel.exchangeDeclare(RabbitConstant.EXCHANGE_WEATHER_DIRECT, "direct");
        channel.queueDeclare(RabbitConstant.QUEUE_CN_DIRECT, false, false, false, null);

        // queueBind 用于将队列与交换机绑定
        // 参数1：队列名称
        // 参数2：交换机名称
        // 参数3：路由key
        channel.queueBind(RabbitConstant.QUEUE_CN_DIRECT, RabbitConstant.EXCHANGE_WEATHER_DIRECT, "cn");
        channel.basicQos(1);
        channel.basicConsume(RabbitConstant.QUEUE_CN_DIRECT, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("BaiduWeather收到中国气象信息：" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }

}
