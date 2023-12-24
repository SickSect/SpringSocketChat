package com.sp.chat.server;

import com.sp.chat.model.Message;
import com.sp.chat.model.User;
import com.sp.chat.service.ChatService;
import com.sp.chat.utils.ChatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {
    /*private Queue<String> messages = new ConcurrentLinkedQueue<>();*/
/*    private Map<String, String> usersOnline = new ConcurrentHashMap<>();*/
    private ChatUtils utils = new ChatUtils();
    @Autowired
    private ChatService service;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@RequestParam("name")String name){
        ResponseEntity<String> response = utils.validateName(name, service.getOnlineUsers());
        if (response != null)
            return response;
        service.createAndSaveUser(name);
        return ResponseEntity.ok().body("You are logged in");
    }

    @PostMapping("/say")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> say(@RequestParam("name")String name, @RequestParam("msg")String msg){
        Message message = new Message();
        message.setMsg(msg);
        message.setUser(service.getUserByLogin(name));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/chat")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Message>> chat(){
        return service.messages();
    }
}
