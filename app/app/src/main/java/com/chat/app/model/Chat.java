package com.chat.app.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String senderNickname;
    private String recipientNickname;
}
