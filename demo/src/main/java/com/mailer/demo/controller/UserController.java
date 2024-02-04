package com.mailer.demo.controller;

import com.mailer.demo.dto.ChatUser;
import com.mailer.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public ChatUser addUser(@Payload ChatUser user){
        if (userService.ifExists(user) == true){
            System.out.println("Add user: " + user.toString());
            userService.saveUser(user);
            return user;
        }
        else {
            System.out.println("User already exists.");
            return null;
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<ChatUser>> findAllOnlineUsers(){
        System.out.println("Find ONLINE users:\n" + userService.findAllOnline());
        return ResponseEntity.ok(userService.findAllOnline());
    }
}
