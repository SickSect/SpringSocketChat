package com.chat.app.service;

import com.chat.app.repo.MessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private MessageRepo messageRepo;
}
