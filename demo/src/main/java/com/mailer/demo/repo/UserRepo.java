package com.mailer.demo.repo;

import com.mailer.demo.dto.ChatUser;
import com.mailer.demo.dto.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<ChatUser,String> {
    List<ChatUser> findAllByStatus(UserStatus status);
}
