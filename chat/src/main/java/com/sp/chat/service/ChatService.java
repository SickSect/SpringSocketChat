package com.sp.chat.service;

import com.sp.chat.dao.MessageDao;
import com.sp.chat.dao.UserDao;
import com.sp.chat.model.Message;
import com.sp.chat.model.Status;
import com.sp.chat.model.User;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private MessageDao messageDao;

    @Nullable
    @Transactional
    public void login(@NotNull String login){
        User user = new User();
        userDao.save(user);
    }

    @NotNull
    @Transactional
    public List<User> getOnlineUsers(){
        return new ArrayList<>(userDao.findAllOnline());
    }

    @Transactional
    public User getUserByLogin(String login){
        return userDao.getByLogin(login);
    }

    @Transactional
    public ResponseEntity messages(){
        List<Message> list= messageDao.getAllMessageByDate();
        return ResponseEntity.ok().body(list);
    }

    public void createAndSaveUser(String name) {
        User user = new User();
        user.setLogin(name);
        user.setStatus(Status.ONLINE);
        userDao.save(user);
    }
}