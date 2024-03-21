package com.mailer.demo.service;

import com.mailer.demo.config.OwnUserPrincipal;
import com.mailer.demo.dto.ChatUser;
import com.mailer.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OwnUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ChatUser user = userRepo.findByNickName(username);
        if (user == null){
            throw new UsernameNotFoundException(username);
        }
        return new OwnUserPrincipal(user);
    }
}
