package com.chat.app.service;

import com.chat.app.model.ChatUser;
import com.chat.app.model.NotificationResponse;
import com.chat.app.model.NotificationStatus;
import com.chat.app.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    @Transactional
    public NotificationResponse userCheckLogin(ChatUser userCheck) {
        ChatUser user = userRepo.findChatUserByNickname(userCheck.getNickname());
        if (user == null){
            return NotificationResponse.builder()
                    .message("User not found")
                    .code(404)
                    .reason("Not found")
                    .type(NotificationStatus.NOT_FOUND)
                    .build();
        }
        //TODO SHA256
        else if (user.getPassword().equals(userCheck.getPassword()) &&
        user.getNickname().equals(userCheck.getNickname()) &&
        user.getFullname().equals(userCheck.getFullname())){
            return NotificationResponse.builder()
                    .message("User login in app")
                    .code(200)
                    .reason("Right data")
                    .type(NotificationStatus.OK)
                    .build();
        }
        else{
            return NotificationResponse.builder()
                    .message("User input have wrong password or login")
                    .code(400)
                    .reason("Wrong data")
                    .type(NotificationStatus.WRONG_INPUT)
                    .build();
        }
    }
}
