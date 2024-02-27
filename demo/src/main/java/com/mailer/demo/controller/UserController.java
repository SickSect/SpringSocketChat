package com.mailer.demo.controller;

import com.mailer.demo.dto.ChatUser;
import com.mailer.demo.dto.Message;
import com.mailer.demo.dto.UserStatus;
import com.mailer.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<ChatUser>> findAllUsers(){
        log.info("Find all users.");
        return ResponseEntity.ok(userService.findAll());
    }

    /*@MessageMapping("/user.ifOnline")
    @SendToUser("/topic/check-online")
    public void checkIfOnline(Message msg, Principal principal){
        log.info("Checking user " + msg.getSenderId() + "if online");
        ChatUser user = userService.getByNickname(msg.getRecipientId());
        template.convertAndSendToUser(msg.getSenderId(), "/queue/online",
                ChatUser.builder()
                        .nickName(user.getNickName())
                        .fullName(user.getFullName())
                        .status(user.getStatus())
                        .build());
    }*/
}
