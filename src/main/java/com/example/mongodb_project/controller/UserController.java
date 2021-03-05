package com.example.mongodb_project.controller;

import com.example.mongodb_project.entity.User;
import com.example.mongodb_project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class UserController{
    private final UserService userService;

    @RequestMapping(value = "/registration", method = {RequestMethod.HEAD, RequestMethod.POST})
    @ResponseStatus(value = HttpStatus.OK)
    public User registration(User user) {
        return userService.insert(user);
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseStatus(value = HttpStatus.OK)
    public User login(User userParam) {
        User user = (User) userService.loadUserByUsername(userParam.getUserID());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(userParam.getPassword(), user.getPassword()))
            return user;
        else
            throw new UsernameNotFoundException("사용자 없음");
    }

    @GetMapping(value = "")
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> show() {
        return userService.selectAll();
    }
}
