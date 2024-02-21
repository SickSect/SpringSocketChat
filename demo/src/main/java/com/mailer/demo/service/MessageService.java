package com.mailer.demo.service;

import com.mailer.demo.dto.Message;
import com.mailer.demo.repo.MessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepo messageRepo;
    private final ChatService chatService;

    public MessageService(MessageRepo messageRepo, ChatService chatService) {
        this.messageRepo = messageRepo;
        this.chatService = chatService;
    }


    public Message save(Message msg){
        String chatId = chatService.getChatId(msg.getSenderId(), msg.getRecipientId(), true)
                .orElseThrow();
        msg.setChatId(chatId);
        messageRepo.save(msg);
        return msg;
    }

    public List<Message> getAllMessages(String senderId, String recipientId){
        var chatId = chatService.getChatId(senderId,recipientId,false);
        return chatId
                .map(messageRepo::findAllByChatId)
                .orElse(new ArrayList<>());
    }

}
