package com.chat.app.controller;

import com.chat.app.model.ChatUser;
import com.chat.app.model.NotificationResponse;
import com.chat.app.model.NotificationStatus;
import com.chat.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final SimpMessagingTemplate template;
    git
    @MessageMapping("/user.login")
    @SendToUser("/user/login")
    public void onUserConnection(Principal principal, ChatUser user){
        //checks - creating - response - login
        logger.info("PRINCIPAL: '{} \n USER INFO: '{}' '{}' '{}'",
                principal.getName(), user.getFullname(),
                user.getNickname(), user.getPassword());
    }

    @MessageMapping("/user.registration")
    @SendToUser("/user/registration")
    public void onRegistration(Principal principal, ChatUser user){
        //checks - creating - response - registration
        logger.info("PRINCIPAL: '{} \n USER INFO: '{}' '{}' '{}'",
                principal.getName(), user.getFullname(),
                user.getNickname(), user.getPassword());
        NotificationResponse response = userService.userCheckLogin(user);
    }
}
