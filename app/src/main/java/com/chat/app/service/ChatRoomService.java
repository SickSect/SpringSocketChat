package com.chat.app.service;

import com.chat.app.model.ChatRoom;
import com.chat.app.repo.ChatRoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {

    private final ChatRoomRepo chatRoomRepository;

    public ChatRoomService(ChatRoomRepo chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public Optional<String> getChatRoomId(String senderId, String recipientId, boolean createRoomIfNotExists){
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(
                () -> {
                    if (createRoomIfNotExists){
                        String chatId = createChat(senderId, recipientId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                }
        );
    }

    private String createChat(String senderId, String recipientId) {
        String chatId = String.format("%s_%s", senderId, recipientId);
        ChatRoom senderRecipient = ChatRoom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();
        ChatRoom recipientSender = ChatRoom.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();
        chatRoomRepository.save(recipientSender);
        chatRoomRepository.save(senderRecipient);
        return chatId;
    }
}
