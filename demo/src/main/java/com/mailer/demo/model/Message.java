package com.mailer.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="messages")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String content;
    private String senderId;
    private String recipientID;
    private String chatId;
    private Date tinestamp;
}
