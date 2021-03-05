package com.example.mongodb_project.repository;

import com.example.mongodb_project.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
    void deleteByUserID(String userID);
    Optional<User> findByUserId(String userId);
}
