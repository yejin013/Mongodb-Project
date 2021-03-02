package com.example.mongodb_project.controller;

import com.example.mongodb_project.entity.User;
import com.example.mongodb_project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class UserController{
    private final UserService userService;

    @PostMapping(value = "")
    @ResponseStatus(value = HttpStatus.OK)
    public User save(@RequestBody User user) {
        return userService.insert(user);
    }

    @GetMapping(value = "/hello")
    @ResponseStatus(value = HttpStatus.OK)
    public String print() {
        return "hello";
    }

    @GetMapping(value = "")
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> show() {
        return userService.selectAll();
    }
}
