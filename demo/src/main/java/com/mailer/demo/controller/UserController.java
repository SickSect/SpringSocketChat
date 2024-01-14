package com.mailer.demo.controller;

import com.mailer.demo.dto.UserDto;
import com.mailer.demo.mapper.UserMapper;
import com.mailer.demo.model.User;
import com.mailer.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final UserMapper userMapper;

    @PostMapping("/registration")
    public ResponseEntity<Void> register(@RequestBody UserDto dto){
        log.info("REGISTRATION USER: " + dto.getNickName());
        // TODO - check if exists
        User entity = userMapper.toEntity(dto);
        service.create(entity);
        return ResponseEntity.ok().build();
    }

}
