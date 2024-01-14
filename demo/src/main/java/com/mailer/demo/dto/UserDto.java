package com.mailer.demo.dto;

import com.mailer.demo.model.UserStatus;
import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String nickName;
    private String fullName;
    private UserStatus status;
}
