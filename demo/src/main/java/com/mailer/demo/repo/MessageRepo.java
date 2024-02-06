package com.mailer.demo.repo;

import com.mailer.demo.dto.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message, String> {
    List<Message> findAllByChatId(String chatId);
}
