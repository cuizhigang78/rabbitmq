package com.cuizg.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName RabbitUtils
 * @Author Maxwell
 * @Date 2021/9/16 22:59
 * @Description RabbitUtils
 * @Version 1.0
 */
public class RabbitUtils {

   private static ConnectionFactory connectionFactory = new ConnectionFactory();

    static {
        connectionFactory.setHost("192.168.1.4");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
    }


    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
