package com.socket.test.controller;

import com.socket.test.model.Greeting;
import com.socket.test.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(Message msg) throws InterruptedException {
        //Thread.sleep(5000); // mock
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(msg.getContent()) + "!");
    }
}
