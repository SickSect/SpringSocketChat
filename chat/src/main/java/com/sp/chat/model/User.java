package com.sp.chat.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user", schema = "chat")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "login", unique = true, nullable = false, length = 25)
    private String login;
}
