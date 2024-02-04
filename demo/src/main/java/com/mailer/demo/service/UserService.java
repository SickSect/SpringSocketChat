package com.mailer.demo.service;

import com.mailer.demo.dto.ChatUser;
import com.mailer.demo.dto.UserStatus;
import com.mailer.demo.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public List<ChatUser> findAllOnline(){
        return userRepo.findAllByStatus(UserStatus.ONLINE);
    }
    @Transactional
    public void saveUser(ChatUser user){
        userRepo.save(user);
    }

    @Transactional
    public boolean ifExists(ChatUser user){
        if (userRepo.findByFullName(user.getFullName()) != null)
            return false;
        if (userRepo.findByNickName(user.getNickName()) != null)
            return false;
        return true;
    }
}
