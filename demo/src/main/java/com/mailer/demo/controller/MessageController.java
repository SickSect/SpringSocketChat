package com.mailer.demo.controller;

import com.mailer.demo.dto.Message;
import com.mailer.demo.dto.Notification;
import com.mailer.demo.dto.ResponseMessage;
import com.mailer.demo.service.ChatService;
import com.mailer.demo.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MessageController {
    private final ChatService chatService;
    private final MessageService messageService;
    private final SimpMessagingTemplate template;

    @MessageMapping("/private-chat")
    @SendToUser("/topic/private-messages")
    public void processMessage(Message msg, Principal principal) {
        log.info("Recieved msg from '{}' to '{}' ", principal.getName(), msg.getRecipientId());
        Message saved = messageService.save(msg);
        template.convertAndSendToUser(msg.getRecipientId(), "/queue/messages",
                Notification.builder()
                        .id(msg.getId())
                        .recipientId(msg.getRecipientId())
                        .senderId(msg.getSenderId())
                        .content(msg.getContent())
                        .build());
    }

    @MessageMapping("/messages/{senderId}/{recipientId}")
    @SendToUser("/topic/get-chat")
    public void getChat(@DestinationVariable("senderId") String senderId,
                        @DestinationVariable("recipientId") String recipientId){
        log.info("Getting chat: " + senderId + " and " + recipientId + "/n MSG IS: " + chatService.findChatMessages(senderId, recipientId));
        template.convertAndSendToUser(senderId, "/queue/get-chat",
                chatService.findChatMessages(senderId, recipientId));
    }
    /*@GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<Message>> findChatMessages(@PathVariable String senderId,
                                                          @PathVariable String recipientId) {
        return ResponseEntity
                .ok(chatService.findChatMessages(senderId, recipientId));
    }*/
}
