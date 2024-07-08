package com.raksmey.event_processor.service;


import com.raksmey.event_processor.domain.entity.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {
    private final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private  SimpMessagingTemplate simMessagingTemplate;



    @RabbitListener(queues = "eventQueue")
    public void handleMessage(Event event) {
        logger.info(":::::MessageListener.class::::::handleMessage.method");
        logger.info(":::Received message:{}", event);
        simMessagingTemplate.convertAndSend("/topic/events", event);
    }
}
