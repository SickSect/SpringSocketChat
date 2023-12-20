package com.sp.chat.model;

import lombok.Data;

@Data
public class Message {
    private Integer id;
    private String msg;
    private User user;
}
