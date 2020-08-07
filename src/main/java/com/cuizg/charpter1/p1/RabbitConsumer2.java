package com.cuizg.charpter1.p1;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName RabbitConsumer
 * @Author cuizhigang
 * @Date 2020/8/6 13:06
 * @Description RabbitConsumer 消费者
 * @Version 1.0
 */
public class RabbitConsumer2 {

    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 5672;  // RabbitMQ服务端默认端口

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Address[] addresses = new Address[]{ new Address(IP_ADDRESS, PORT) };
        ConnectionFactory factory = new ConnectionFactory();
        // 默认为guest/guest
        factory.setUsername("guest");
        factory.setPassword("guest");
        // 创建连接
        Connection connection = factory.newConnection(addresses);
        // 创建信道
        Channel channel = connection.createChannel();
        // 设置自动确认为false，需要在代码中操作
        boolean autoAck = false;
        // 设置客户端最多接收未被ack的消息的个数
        channel.basicQos(64);
        channel.basicConsume(QUEUE_NAME, autoAck, "myConsumerTag", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routingKey = envelope.getRoutingKey();
                String contentType = properties.getContentType();
                long deliveryTab = envelope.getDeliveryTag();
                // process the message components here
                channel.basicAck(deliveryTab, false);
            }
        });
        // 等待回调函数执行完毕之后，关闭资源
        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();
    }
}
