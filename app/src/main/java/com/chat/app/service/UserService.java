package com.chat.app.service;

import com.chat.app.model.User;
import com.chat.app.model.UserStatus;
import com.chat.app.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public void saveUser(User user){
        user.setStatus(UserStatus.ONLINE);
        userRepo.save(user);
    }

    public void disconnect(User user){
        User currentUser = userRepo.findById(user.getNickName()).orElse(null);
        if (currentUser != null){
            currentUser.setStatus(UserStatus.OFFLINE);
            userRepo.save(currentUser);
        }
    }

    public List<User> findOnlineUsers(){
        return userRepo.findAllByStatus(UserStatus.ONLINE);
    }
}
