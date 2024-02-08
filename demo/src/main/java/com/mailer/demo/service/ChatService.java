package com.mailer.demo.service;

import com.mailer.demo.dto.Chat;
import com.mailer.demo.dto.Message;
import com.mailer.demo.repo.ChatRepo;
import com.mailer.demo.repo.MessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepo chatRepo;
    private final MessageRepo messageRepo;

    public Optional<String> getChatId(String senderId, String recipientId, boolean createFlag){
        return chatRepo.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(Chat::getChatId)
                .or(
                        () ->
                        {
                            if (createFlag){
                                String chatId = createChat(senderId,recipientId);
                                return Optional.of(chatId);
                            }
                            return Optional.empty();
                        }
                );
    }

    private String createChat(String senderId, String recipientId) {
        String chatId = String.format("%s_%s", senderId,recipientId);
        Chat senderRecipient = Chat.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();
        Chat recipientSender = Chat.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();
        chatRepo.save(senderRecipient);
        chatRepo.save(recipientSender);
        return chatId;
    }


    public List<Message> findChatMessages(String senderId, String recipientId) {
        var chatId = this.getChatId(senderId, recipientId, false);
        return chatId.map(messageRepo::findAllByChatId).orElse(new ArrayList<>());
    }
}
