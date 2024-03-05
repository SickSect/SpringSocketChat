package com.chat.app.service;

import com.chat.app.repo.ChatRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private ChatRepo chatRepo;
}
