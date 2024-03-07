package com.chat.app.controller;

import com.chat.app.model.ChatUser;
import com.chat.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @MessageMapping("/user.login")
    @SendToUser("/user/login")
    public void onUserConnection(Principal principal, ChatUser user){
        //checks - creating - response - login
        logger.debug("PRINCIPAL: '{} \n USER INFO: '{}' '{}' '{}'", principal.getName(), user.getFullname(), user.getNickname(), user.getPassword());
    }
}
