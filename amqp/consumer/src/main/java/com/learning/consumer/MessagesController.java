package com.learning.consumer;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private MessageReceiver receiver;

    @GetMapping
    public Map<LocalDateTime, String> getMessages() {
        return receiver.getMessages();
    }

}
