package com.example.mongodb_project.service;

import com.example.mongodb_project.entity.User;
import com.example.mongodb_project.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

public class UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRepo userRepo;

    private void insert(String userID, String name, String address, String password) {
        User entity = User.builder()
                .userID(userID)
                .name(name)
                .address(address)
                .password(password)
                .build();

        userRepo.save(entity);
        mongoTemplate.insert(entity);
    }

    private void selectAll() {
        List<User> list = userRepo.findAll();
        List<User> listMongo = mongoTemplate.findAll(User.class);
    }

    private void selectPaging() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<User> page = userRepo.findAll(pageable);

        Query query = new Query().with(pageable);
        List<User> list = mongoTemplate.find(query, User.class);

        Page<User> pageMongo = PageableExecutionUtils.getPage(
                list,
                pageable,
                ()-> mongoTemplate.count(new Query().limit(-1).skip(-1), User.class)
        );
    }

    private void delete(String userID) {
        userRepo.deleteByUserID(userID);
    }
}
