package com.sp.chat.utils;

import com.sp.chat.model.User;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class ChatUtils {

    public ResponseEntity<String> validateName(String name, List<User> usersList) {
        if (name.length() <= 2 || name.length() >= 25)
            return ResponseEntity.badRequest().body("Username must be between 2 and 25 symbols");
        for (User user : usersList) {
            if (user.getLogin().equals(name))
                return ResponseEntity.badRequest().body("User already exists");
        }
        return null;
    }

    public ResponseEntity<String> validateSending(User user, String msg) {
        if (user == null)
            return ResponseEntity.badRequest().body("User does not exist.");
        return ResponseEntity.ok().body("Message sending success.");
    }
}
