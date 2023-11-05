package com.cuizg.topic;

import com.cuizg.constants.RabbitConstant;
import com.cuizg.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName WeatherBureauTopic
 * @Author Maxwell
 * @Date 2021/9/24 23:19
 * @Description WeatherBureauTopic
 * @Version 1.0
 */
public class WeatherBureauTopic {

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection conn = RabbitUtils.getConnection();
        Channel channel = conn.createChannel();

        Map<String, String> area = new HashMap<>();
        area.put("cn.hunan.changsha.20201127", "中国湖南长沙20201127天气数据");
        area.put("cn.hubei.wuhan.20201127", "中国湖北武汉20201127天气数据");
        area.put("cn.hunan.zhuzhou.20201127", "中国湖南株洲20201127天气数据");
        area.put("us.cal.lsj.20201127", "美国加州洛杉矶20201127天气数据");
        area.put("cn.hebei.shijiazhuang.20201128", "中国河北石家庄20201128天气数据");
        area.put("cn.hubei.wuhan.20201128", "中国湖北武汉20201128天气数据");
        area.put("cn.henan.zhengzhou.20201128", "中国河南郑州20201128天气数据");
        area.put("us.cal.lsj.20201128", "美国加州洛杉矶20201128天气数据");
        
        channel.exchangeDeclare(RabbitConstant.EXCHANGE_WEATHER_TOPIC, "topic");
        for (Map.Entry<String, String> entry : area.entrySet()) {
            channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER_TOPIC, entry.getKey(), null, entry.getValue().getBytes());
        }

        channel.close();
        conn.close();
    }
}
