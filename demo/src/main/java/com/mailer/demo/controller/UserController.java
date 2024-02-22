package com.mailer.demo.controller;

import com.mailer.demo.dto.ChatUser;
import com.mailer.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final SimpMessagingTemplate template;
    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public ChatUser addUser(@Payload ChatUser user, Principal principal){
        if (userService.ifExists(user)){
            log.info("Add user: " + principal.getName() + " " + user.toString());
            user.setId(principal.getName());
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
        log.info("Find ONLINE users:\n" + userService.findAllOnline());
        return ResponseEntity.ok(userService.findAllOnline());
    }
}
