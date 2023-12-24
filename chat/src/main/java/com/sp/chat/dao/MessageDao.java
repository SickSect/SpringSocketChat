package com.sp.chat.dao;

import com.sp.chat.model.Message;

import java.util.List;

public interface MessageDao {
    List<Message> getAllMessageByDate();
}
