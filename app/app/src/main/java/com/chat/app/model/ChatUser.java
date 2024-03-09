package com.chat.app.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@Table(name = "chat_user")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ChatUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nickname;
    private String fullname;
    private String password;
    private UserStatus status;
}
