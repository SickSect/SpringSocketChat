package com.chat.app.repo;

import com.chat.app.model.User;
import com.chat.app.model.UserStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepo extends MongoRepository<User, String> {
    List<User> findAllByStatus(UserStatus userStatus);
}
