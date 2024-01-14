package com.mailer.demo.repo;

import com.mailer.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {

}
