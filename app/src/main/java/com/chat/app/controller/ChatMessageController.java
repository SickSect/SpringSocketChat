package com.chat.app.controller;

import com.chat.app.model.ChatMessage;
import com.chat.app.model.ChatNotification;
import com.chat.app.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage){
        ChatMessage saved = chatMessageService.save(chatMessage);
        simpMessagingTemplate.convertAndSendToUser(saved.getRecipientId(),"/queue/messages",
                ChatNotification.builder()
                        .id(saved.getId())
                        .senderId(saved.getRecipientId())
                        .recipientId(saved.getRecipientId())
                        .content(saved.getContent())
                .build());
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(
            @PathVariable("senderId")String senderId,
            @PathVariable("recipientId") String recipientId){
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId,recipientId));
    }
}
