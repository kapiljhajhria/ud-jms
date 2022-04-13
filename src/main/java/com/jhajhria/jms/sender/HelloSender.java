package com.jhajhria.jms.sender;

import com.jhajhria.jms.config.JmsConfig;
import com.jhajhria.jms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        System.out.println("I am sending Hello World");
        HelloWorldMessage message=  HelloWorldMessage.builder()
                .message("Hello World - sendMessage method from HelloSender")
                .id(UUID.randomUUID())
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

        System.out.println("Message Sent");

    }
}