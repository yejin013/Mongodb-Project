package com.example.mongodb_project.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "user")
@ToString
public class User {

    @Id
    private String userID;
    private String name;
    private String address;
    private String password;

    @Builder
    public User(String userID, String name, String address, String password) {
        this.userID = userID;
        this.name = name;
        this.address = address;
        this.password = password;
    }
}
