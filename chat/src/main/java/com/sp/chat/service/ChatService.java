package com.sp.chat.service;

import com.sp.chat.dao.MessageDao;
import com.sp.chat.dao.UserDao;
import com.sp.chat.model.User;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    private UserDao userDao;
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
        return new ArrayList<>(userDao.findAll());
    }
}
