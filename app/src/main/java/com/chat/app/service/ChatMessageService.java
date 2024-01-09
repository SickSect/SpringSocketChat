package com.chat.app.service;

import com.chat.app.model.ChatMessage;
import com.chat.app.repo.ChatMessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageService {
    private final ChatMessageRepo chatMessageRepo;
    private final ChatRoomService chatRoomService;

    public ChatMessageService(ChatMessageRepo chatMessageRepo, ChatRoomService chatRoomService) {
        this.chatMessageRepo = chatMessageRepo;
        this.chatRoomService = chatRoomService;
    }

    public ChatMessage save(ChatMessage input){
        var chatId = chatRoomService.getChatRoomId(input.getSenderId(), input.getRecipientId(), true)
                .orElseThrow();
        input.setChatId(chatId);
        chatMessageRepo.save(input);
        return input;
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId){
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        //List<ChatMessage> list = chatMessageRepo.findAllByChatId(chatId);
        return chatId.map(chatMessageRepo::findAllByChatId).orElse(new ArrayList<>());
    }
}
