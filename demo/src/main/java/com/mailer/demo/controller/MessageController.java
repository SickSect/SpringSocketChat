package com.mailer.demo.controller;

import com.mailer.demo.dto.Message;
import com.mailer.demo.dto.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
@Slf4j
public class MessageController {
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(@Payload final Message message){
        log.info("Received message:\n content:" + message.getContent() + "\n sender nickname: " + message.getSender());
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getContent()), "201");
    }
}
