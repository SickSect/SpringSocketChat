package com.mailer.demo.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  String id;
    private String chatId;
    private String senderId;
    private String recipientId;
}
