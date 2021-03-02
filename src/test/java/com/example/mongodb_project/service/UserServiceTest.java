package com.example.mongodb_project.service;

import com.example.mongodb_project.entity.User;
import com.example.mongodb_project.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;

    @Test
    void signup() {
        User user = new User("yejin", "yejin", "yejin013@naver.com", "jin12345");

        userService.insert(user);
    }

}
