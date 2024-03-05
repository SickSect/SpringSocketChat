package com.chat.app.repo;

import com.chat.app.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepo extends JpaRepository<Chat, String> {
}
