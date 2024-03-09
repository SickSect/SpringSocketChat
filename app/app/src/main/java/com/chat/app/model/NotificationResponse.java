package com.chat.app.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
// TODO add http maybe?
public class NotificationResponse{
    private String reason;
    private String message;
    private Integer code;
    private NotificationStatus type;
}
