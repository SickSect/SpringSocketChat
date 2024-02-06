package com.mailer.demo.service;

import com.mailer.demo.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final UserService userService;

    public Message save(Message msg){
        String chatId =
    }
}
