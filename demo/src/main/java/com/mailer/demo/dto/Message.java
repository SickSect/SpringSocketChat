package com.mailer.demo.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
@Entity
*/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String id;
    private String content;
    private String sender;


    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
