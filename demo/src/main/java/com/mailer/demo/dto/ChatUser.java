package com.mailer.demo.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nickName;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
