package com.cuizg.helloworld;

import com.cuizg.constants.RabbitConstant;
import com.cuizg.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Producer
 * @Author Maxwell
 * @Date 2021/9/15 23:10
 * @Description Producer
 * @Version 1.0
 */
public class Producer {

    public static void main(String[] argv) throws IOException, TimeoutException {
        // 获取TCP长连接
        Connection conn = RabbitUtils.getConnection();
        // 创建通信“通道”，相当于TCP中的虚拟连接
        Channel channel = conn.createChannel();

        // 声明并创建一个队列，如果队列已存在，则直接使用这个队列
        // 参数1：队列名称
        // 参数2：是否持久化，false表示不持久化，此时MQ停机数据就会丢失
        // 参数3：是否队列私有化，false表示所有消费者都可以访问，true表示只有第一次拥有它的消费者才能访问
        // 参数4：是否自动删除，false表示连接停止后不自动删除这个队列
        // 参数5：其他额外参数
        channel.queueDeclare(RabbitConstant.QUEUE_HELLO_WORLD, false, false, false, null);

        String message = "hello world! now time is " + System.currentTimeMillis();
        // 发送消息
        // 参数1：exchange交换机，暂时用不到，发布订阅模式时才会使用
        // 参数2：队列名称
        // 参数3：额外的设置属性
        // 参数4：要发送的消息字节数组
        channel.basicPublish("", RabbitConstant.QUEUE_HELLO_WORLD, null, message.getBytes());
        channel.close();
        conn.close();
        System.out.println(String.format(" === 消息:%s 发送成功 ===", message));
    }
}
