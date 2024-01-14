package com.mailer.demo.controller;


import com.mailer.demo.model.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, ChatMessage msg){
        log.info("SEND MESSAGE TO " + to + " MESSAGE IS: " + msg.getContent());
        // check if user exists
        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, msg);
    }
}
