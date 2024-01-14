package com.chat.app.repo;

import com.chat.app.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ChatMessageRepo extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findAllByChatId(String chatId);
}
