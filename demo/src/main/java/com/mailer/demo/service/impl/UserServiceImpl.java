package com.mailer.demo.service.impl;

import com.mailer.demo.model.User;
import com.mailer.demo.repo.UserRepo;
import com.mailer.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    @Override
    public User create(final User user) {
        if (userRepo.findById(user.getId()).isPresent())
            throw new IllegalStateException("User already exists");
        // TODO - add password confirmation
        // TODO - add decoder encoder
        userRepo.save(user);
        return user;
    }
}
