package com.sp.chat.dao;

import com.sp.chat.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao{
    User getByLogin(String login);
    void save(User user);
    List<User> findAll();
}
