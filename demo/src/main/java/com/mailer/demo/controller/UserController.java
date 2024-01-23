package com.mailer.demo.controller;

import com.mailer.demo.dto.ChatUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class UserController {
    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public ChatUser addUser(@Payload ChatUser user){
        System.out.println("Add user: " + user.getNickname());
        return user;
    }
}
