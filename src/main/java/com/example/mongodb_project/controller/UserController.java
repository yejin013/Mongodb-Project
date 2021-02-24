package com.example.mongodb_project.controller;

import com.example.mongodb_project.entity.User;
import com.example.mongodb_project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@AllArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/user/signup")
    public ResponseEntity<User> save(@RequestBody User user) {
        return new ResponseEntity<User>(userService.insert(user), HttpStatus.OK);
    }

    @GetMapping(value = "/user/select")
    public ResponseEntity<List<User>> show() {
        return new ResponseEntity<List<User>>(userService.selectAll(), HttpStatus.OK);
    }
}
