package com.mailer.demo.repo;

import com.mailer.demo.dto.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepo extends JpaRepository<Chat, String> {
    Optional<Chat> findBySenderIdAndRecipientId(String senderId, String recipientId);

}
