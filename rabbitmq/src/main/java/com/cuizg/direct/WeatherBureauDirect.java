package com.cuizg.direct;

import com.cuizg.constants.RabbitConstant;
import com.cuizg.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName WeatherBureauDirect
 * @Author Maxwell
 * @Date 2021/9/24 23:19
 * @Description WeatherBureauDirect
 * @Version 1.0
 */
public class WeatherBureauDirect {

    public static void main(String[] args) throws IOException, TimeoutException {

        List<WeatherMessage> weatherMessages = new LinkedList<>();
        weatherMessages.add(new WeatherMessage("cn", "中国湖南长沙20201127天气数据"));
        weatherMessages.add(new WeatherMessage("cn", "中国湖北武汉20201127天气数据"));
        weatherMessages.add(new WeatherMessage("cn", "中国湖南株洲20201127天气数据"));
        weatherMessages.add(new WeatherMessage("us", "美国加州洛杉矶20201127天气数据"));
        weatherMessages.add(new WeatherMessage("cn", "中国河北石家庄20201128天气数据"));
        weatherMessages.add(new WeatherMessage("cn", "中国湖北武汉20201128天气数据"));
        weatherMessages.add(new WeatherMessage("cn", "中国河南郑州20201128天气数据"));
        weatherMessages.add(new WeatherMessage("us", "美国加州洛杉矶20201128天气数据"));

        Connection conn = RabbitUtils.getConnection();
        Channel channel = conn.createChannel();

        channel.exchangeDeclare(RabbitConstant.EXCHANGE_WEATHER_DIRECT, "direct");
        for (WeatherMessage weatherMessage : weatherMessages) {
            channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER_DIRECT, weatherMessage.getCountry(), null, weatherMessage.getDesc().getBytes());
        }
        channel.close();
        conn.close();
    }
}
