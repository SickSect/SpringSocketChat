package com.chat.app.repo;

import com.chat.app.model.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<ChatUser, String> {
    Optional<ChatUser> findChatUserByNickname(String nickname);
}
