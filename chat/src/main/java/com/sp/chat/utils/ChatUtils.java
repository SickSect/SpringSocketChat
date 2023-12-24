package com.sp.chat.utils;

import com.sp.chat.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ChatUtils {

    public ResponseEntity<String> validateName(String name, List<User> usersList){
        if (name.length() <= 2 || name.length() >= 25)
            return ResponseEntity.badRequest().body("Username must be between 2 and 25 symbols");
        else if (!usersList.stream()
                .allMatch(login -> name.equals(login)))
            return ResponseEntity.badRequest().body("User already exists");
        else
            return null;
    }
}
