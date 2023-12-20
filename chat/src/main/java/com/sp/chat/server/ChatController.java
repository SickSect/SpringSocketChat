package com.sp.chat.server;

import com.sp.chat.utils.ChatUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private Queue<String> messages = new ConcurrentLinkedQueue<>();
    private Map<String, String> usersOnline = new ConcurrentHashMap<>();
    private ChatUtils utils = new ChatUtils();

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@RequestParam("name")String name){
        ResponseEntity<String> response = utils.validateName(name, usersOnline);
        if (response != null)
            return response;
        usersOnline.put(name, name);
        messages.add("User: " + name + " logged in!");
        return ResponseEntity.ok().body("You are logged in");
    }



    @PostMapping("/say")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> say(@RequestParam("name")String name, @RequestParam("msg")String msg){
        messages.add(name + ": " + msg);
        return ResponseEntity.ok().build();
    }
}
