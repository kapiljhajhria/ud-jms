package com.jhajhria.jms.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhajhria.jms.config.JmsConfig;
import com.jhajhria.jms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
//        System.out.println("I am sending Hello World");
        HelloWorldMessage message=  HelloWorldMessage.builder()
                .message("Hello World - sendMessage method from HelloSender")
                .id(UUID.randomUUID())
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

//        System.out.println("Message Sent");

    }

    @Scheduled(fixedRate = 1000)
    public void sendReceiveMessage() throws JMSException {

        HelloWorldMessage message=  HelloWorldMessage.builder()
                .message("Hello World - sendReceiveMessage method from HelloSender")
                .id(UUID.randomUUID())
                .build();

       Message receivedMessage =  jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RCV_QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message helloMessage = null;
                try {
                    helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                    helloMessage.setStringProperty("_type", HelloWorldMessage.class.getName());
                    System.out.println("Sending Hello back");
                    return helloMessage;
                } catch (JsonProcessingException e) {
                    throw  new JMSException(e.getMessage());
//                    throw new RuntimeException(e);
                }
            }
        });
        System.out.println(receivedMessage.getBody(String.class));
    }
}