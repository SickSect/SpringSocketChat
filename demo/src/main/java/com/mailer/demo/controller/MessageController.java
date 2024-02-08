package com.mailer.demo.controller;

import com.mailer.demo.dto.Message;
import com.mailer.demo.dto.ResponseMessage;
import com.mailer.demo.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Controller
@Slf4j
public class MessageController {
    private final ChatService chatService
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(@Payload final Message message){
        log.info("Received message:\n content:" + message.getContent() + "\n sender nickname: " + message.getSender());
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getContent()), "201");
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload Message msg){
        Message saved;
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<Message>> findChatMessages(@PathVariable String senderId,
                                                          @PathVariable String recipientId) {
        return ResponseEntity
                .ok(chatService.findChatMessages(senderId, recipientId));
    }
}
