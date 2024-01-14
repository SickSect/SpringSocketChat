package com.mailer.demo.config;

import com.mailer.demo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final ApplicationContext applicationContext;

}
