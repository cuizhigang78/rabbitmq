package com.cuizg.workqueue;

import com.cuizg.constants.RabbitConstant;
import com.cuizg.utils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @ClassName SMSSender10
 * @Author Maxwell
 * @Date 2021/9/24 0:27
 * @Description SMSSender10
 * @Version 1.0
 */
public class SMSSender {
    public static void main(final String[] args) throws IOException {
        System.out.println("我处理一条消息需要额外: " + args[0] + "ms");
        Connection connection = RabbitUtils.getConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(RabbitConstant.QUEUE_SMS, false, false, false, null);

        // 如果不写basicQos(1)，则MQ会自动将所有请求平均发送给所有消费者
        // basicQos：MQ不再对消费者一次发送多个请求，而是消费者处理完一个消息（确认后），再从队列中获取一个新的消息。
        channel.basicQos(1);
        /*
          场景一：三个消费者处理能力相同，未开启basicQos
          现象：三个消费者采用轮询算法依次获取消息
          结论：未开启basicQos，三者轮询

          场景二：三个消费者处理能力不同，未开启basicQos
          现象：仍然是三个消费者处理平均分配100条消息，但由于处理能力不同，所以处理完成时间也不同
          结论：未开启basicQos，消费者在处理消息前，就已经将消息分配完成

          场景三：三个消费者处理能力不同，开启basicQos
          现象：能者多劳，处理能力高的处理数量多，处理能力低的处理数量少
          结论：开启basicQos，不事先分配消息到各个消费者，而是在运行过程中，消费者处理完成后，自动拉取消息

          总结：basicQos是充分利用各消费者处理能力的关键
         */
        channel.basicConsume(RabbitConstant.QUEUE_SMS, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String jsonSMS = new String(body);
                System.out.println(envelope.getDeliveryTag() + " 短信发送成功：" + jsonSMS);
                try {
                    // 通过睡眠时间不同，模拟不同消费者的处理能力
                    Thread.sleep(Long.valueOf(args[0]));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
