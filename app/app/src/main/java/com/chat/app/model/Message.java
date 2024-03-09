package com.chat.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@ToString
@Entity
@Table(name="message")
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String senderNickname;
    private String recipientNickname;
    private String content;
    private Date timestamp;
    private String chatId;
}
