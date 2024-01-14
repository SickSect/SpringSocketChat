package com.mailer.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "chat_rooms")
@Data
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String senderId;
    private String recipientId;
}
