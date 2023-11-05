package com.cuizg.fanout;

import com.cuizg.constants.RabbitConstant;
import com.cuizg.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName WeatherBureauDirect
 * @Author Maxwell
 * @Date 2021/9/24 23:19
 * @Description WeatherBureauDirect
 * @Version 1.0
 */
public class WeatherBureauFanout {

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection conn = RabbitUtils.getConnection();
        String input = new Scanner(System.in).next();
        Channel channel = conn.createChannel();

        channel.exchangeDeclare(RabbitConstant.EXCHANGE_WEATHER_FANOUT, "fanout");
        channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER_FANOUT, "", null, input.getBytes());

        channel.close();
        conn.close();
    }
}
