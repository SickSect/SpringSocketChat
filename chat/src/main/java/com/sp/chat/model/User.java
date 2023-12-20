package com.sp.chat.model;


import lombok.Data;

@Data
public class User {
    private Integer id;
    private String login;
    private Message message;
}
