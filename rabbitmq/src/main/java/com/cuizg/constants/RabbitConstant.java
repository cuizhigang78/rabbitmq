package com.cuizg.constants;

/**
 * @ClassName RabbitConstant
 * @Author Maxwell
 * @Date 2021/9/16 22:47
 * @Description RabbitConstant
 * @Version 1.0
 */
public class RabbitConstant {

    public static final String QUEUE_HELLO_WORLD = "queue_hello_world";

    public static final String QUEUE_SMS = "queue_sms";

    public static final String QUEUE_SINA_FANOUT = "queue_sina_fanout";
    public static final String QUEUE_BAIDU_FANOUT = "queue_baidu_fanout";

    public static final String QUEUE_CN_DIRECT = "queue_cn_direct";
    public static final String QUEUE_US_DIRECT = "queue_us_direct";
    public static final String QUEUE_ALL_DIRECT = "queue_all_direct";

    public static final String QUEUE_SINA_TOPIC = "queue_sina_topic";
    public static final String QUEUE_BAIDU_TOPIC = "queue_baidu_topic";

    public static final String EXCHANGE_WEATHER_FANOUT = "exchange_weather_fanout";

    public static final String EXCHANGE_WEATHER_DIRECT = "exchange_weather_direct";

    public static final String EXCHANGE_WEATHER_TOPIC = "exchange_weather_topic";
}
