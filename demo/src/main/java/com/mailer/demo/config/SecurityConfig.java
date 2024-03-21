package com.mailer.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers("/", "/login", "/main.js", "/main.css").permitAll();
                    authConfig.anyRequest().authenticated();
                })
                .sessionManagement(session ->
                        session.maximumSessions(1)
                                .maxSessionsPreventsLogin(true)
                                .expiredUrl("/login?expired")
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .failureUrl("/login-error")
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID"));
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(){

    }
}
