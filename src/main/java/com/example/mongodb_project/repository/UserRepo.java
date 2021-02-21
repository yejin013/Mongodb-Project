package com.example.mongodb_project.repository;

import com.example.mongodb_project.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    void deleteByUserID(String userID);
}
