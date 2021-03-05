package com.example.mongodb_project.service;

import com.example.mongodb_project.entity.User;
import com.example.mongodb_project.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Log
@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private MongoTemplate mongoTemplate;
    private UserRepo userRepo;

    public User insert(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        User entity = User.builder()
                .userID(user.getUserID())
                .name(user.getName())
                .address(user.getAddress())
                .password(user.getPassword())
                .build();

        userRepo.save(entity);
//        mongoTemplate.insert(entity);
        return entity;
    }

    public void updateByUserID(String userID, User user) {
        User u = userRepo.findById(userID).orElseThrow(() -> new ResourceAccessException("User"));
        log.info("User : " + user);
        log.info("User : " + u);

        u.setName(user.getName());
        u.setAddress(user.getAddress());
        u.setPassword(user.getPassword());
    }

    public List<User> selectAll() {
        List<User> list = userRepo.findAll();
//        List<User> listMongo = mongoTemplate.findAll(User.class);
        return list;
    }

    private void selectPaging() {
        Pageable pageable = PageRequest.of(0, 10);

//        Page<User> page = userRepo.findAll(pageable);

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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUserId(s).get();
    }
}
