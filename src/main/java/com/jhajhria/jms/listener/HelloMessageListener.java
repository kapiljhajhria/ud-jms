package com.jhajhria.jms.listener;

import com.jhajhria.jms.config.JmsConfig;
import com.jhajhria.jms.model.HelloWorldMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class HelloMessageListener {

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers, Message message) {
//        @Payload annotation will tell spring boot to deserialize the object
//        @Headers annotation will tell spring boot to fetch the headers from the message
//
        System.out.println("Received message: ");
        System.out.println(helloWorldMessage);
    }
}