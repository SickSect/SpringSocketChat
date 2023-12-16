package com.sp.chat.utils;

import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Queue;

public class ChatUtils {

    public ResponseEntity<String> validateName(String name, Map<String,String> usersList){
        if (name.length() <= 2 || name.length() >= 25)
            return ResponseEntity.badRequest().body("Username must be between 2 and 25 symbols");
        else if (usersList.containsKey(name))
            return ResponseEntity.badRequest().body("User already exists");
        else
            return null;
    }
}
