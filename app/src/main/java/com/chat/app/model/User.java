package com.chat.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
    @Id
    private String username;
    private String fullName;
    private UserStatus status;

}
