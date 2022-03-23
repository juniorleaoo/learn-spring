package com.learning.consumer;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private static final Map<LocalDateTime, String> messages = new HashMap<>();

    @RabbitListener(queues = "messages")
    public void receive(String message) {
        if (message != null && !message.isBlank()) {
            messages.put(LocalDateTime.now(), message);
        }
    }

    public Map<LocalDateTime, String> getMessages() {
        return this.messages;
    }

}
