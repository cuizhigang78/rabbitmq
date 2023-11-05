package com.cuizg.fanout;

import com.cuizg.constants.RabbitConstant;
import com.cuizg.utils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @ClassName USWeather
 * @Author Maxwell
 * @Date 2021/9/24 23:27
 * @Description USWeather
 * @Version 1.0
 */
public class SinaFanout {

    public static void main(String[] args) throws IOException {
        Connection conn = RabbitUtils.getConnection();
        Channel channel = conn.createChannel();
        channel.exchangeDeclare(RabbitConstant.EXCHANGE_WEATHER_FANOUT, "fanout");
        channel.queueDeclare(RabbitConstant.QUEUE_SINA_FANOUT, false, false, false, null);

        // queueBind 用于将队列与交换机绑定
        // 参数1：队列名称
        // 参数2：交换机名称
        // 参数3：路由key
        channel.queueBind(RabbitConstant.QUEUE_SINA_FANOUT, RabbitConstant.EXCHANGE_WEATHER_FANOUT, "");
        channel.basicQos(1);
        channel.basicConsume(RabbitConstant.QUEUE_SINA_FANOUT, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("SinaWeather收到气象信息：" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }

}
