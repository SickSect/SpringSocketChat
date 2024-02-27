package com.mailer.demo.service;

import com.mailer.demo.dto.ChatUser;
import com.mailer.demo.dto.UserStatus;
import com.mailer.demo.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    @Transactional
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
        else if (userRepo.findByNickName(user.getNickName()) != null)
            return false;
        return true;
    }

    @Transactional
    public boolean ifOnline(String nickname){
        if (userRepo.findByNickName(nickname).getStatus() == UserStatus.ONLINE)
            return true;
        else
            return false;
    }

    @Transactional
    public List<ChatUser> findAll(){
        return userRepo.findAll();
    }

    @Transactional
    public Optional<ChatUser> getById(String id){
        return userRepo.findById(id);
    }

    @Transactional
    public ChatUser getByNickname(String nickname) {
        return userRepo.findByNickName(nickname);
    }
}
