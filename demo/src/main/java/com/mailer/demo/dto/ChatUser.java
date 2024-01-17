package com.mailer.demo.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class ChatUser {
    private String id;
    private String nickname;
    private UserStatus status;
}
