package com.cuizg.helloworld;

import com.cuizg.constants.RabbitConstant;
import com.cuizg.utils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @ClassName Consumer
 * @Author Maxwell
 * @Date 2021/9/15 23:21
 * @Description Consumer
 * @Version 1.0
 */
public class Consumer {

    public static void main(String[] argv) throws Exception {
        // 获取长TCP连接
        Connection connection = RabbitUtils.getConnection();

        // 创建通信“通道”，相当于TCP中的虚拟连接
        Channel channel = connection.createChannel();

        // 声明并创建一个队列，如果队列已存在，则使用这个队列
        // 参数1：队列名称ID
        // 参数2：是否持久化，false表示不持久化数据，此时MQ停机数据就会丢失
        // 参数3：是否队列私有化，false表示所有消费都可以访问，true表示只有第一次拥有它的消费者可以访问
        // 参数4：是否自动删除，false表示连接停止后不自动删除这个队列
        // 参数5：其他额外的参数
        channel.queueDeclare(RabbitConstant.QUEUE_HELLO_WORLD, false, false, false, null);

        // 从MQ服务器中获取数据
        // 创建一个消息消费者
        // 参数1：队列名
        // 参数2：是否自动确认收到消息，false表示需要手动编程来确认消息，这是MQ的推荐做法
        // 参数3：要传入DefaultConsumer的实现类
        channel.basicConsume(RabbitConstant.QUEUE_HELLO_WORLD, false, new Receiver(channel));
    }

    static class Receiver extends DefaultConsumer {

        private Channel channel;

        public Receiver(Channel channel) {
            super(channel);
            this.channel = channel;
        }

        @Override
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            String message = new String(body);

            System.out.println(String.format("=== 接收到消息：%s ===", message));

            long deliveryTag = envelope.getDeliveryTag();

            System.out.println(String.format("消息的TagId：%d", deliveryTag));

            // false表示只确认签收当前的消息，true表示签收该消息者所有未签收的消息
            channel.basicAck(deliveryTag, false);
        }
    }
}
