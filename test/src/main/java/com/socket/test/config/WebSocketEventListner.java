package com.socket.test.config;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListner {
    @EventListener
    public void handleDisconnectListner(SessionDisconnectEvent event){

    }
}
