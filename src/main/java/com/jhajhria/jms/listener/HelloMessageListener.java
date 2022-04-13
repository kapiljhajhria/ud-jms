package com.jhajhria.jms.listener;

import com.jhajhria.jms.config.JmsConfig;
import com.jhajhria.jms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HelloMessageListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers, Message message) {
//        @Payload annotation will tell spring boot to deserialize the object
//        @Headers annotation will tell spring boot to fetch the headers from the message
//
//        System.out.println("Received message: ");
//        System.out.println(helloWorldMessage);
    }

    @JmsListener(destination = JmsConfig.MY_SEND_RCV_QUEUE)
    public void listenForHello(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers, Message jmsMessage, org.springframework.messaging.Message springMessage) {
//        @Payload annotation will tell spring boot to deserialize the object
//        @Headers annotation will tell spring boot to fetch the headers from the message

        // springMessage param added to check the difference between springMessage and jmsMessage using debugger.
        HelloWorldMessage payloadMessage=  HelloWorldMessage.builder()
                .message("World---- ")
                .id(UUID.randomUUID())
                .build();
        jmsTemplate.convertAndSend((Destination) jmsMessage.getHeaders().get("jms_replyTo"), payloadMessage);
    }
}