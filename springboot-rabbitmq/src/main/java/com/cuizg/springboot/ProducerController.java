package com.cuizg.springboot;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName ProducerController
 * @Author Maxwell
 * @Date 2021/9/25 23:13
 * @Description ProducerController
 * @Version 1.0
 */
@RestController
public class ProducerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/helloWorldSend")
    public String helloWorldSend(String message) throws UnsupportedEncodingException {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        rabbitTemplate.send("", new Message(message.getBytes("UTF-8"), messageProperties));
        return "message has been sent " + message;
    }

    @GetMapping("/fanoutSend")
    public String fanoutSend(String message) throws UnsupportedEncodingException {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        rabbitTemplate.send("", "", new Message(message.getBytes("UTF-8"), messageProperties));
        return "message has been sent " + message;
    }
}
