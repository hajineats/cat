package com.se701.cat.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.se701.cat.MongoConfig;
import com.se701.cat.entity.User;
import com.se701.cat.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    @Autowired
    MongoConfig mongoConfig;

    public void createUser(String name){
        User user = new User(name, 1, 0, 0);
        repository.save(user);
    }


    public User findUser(String username){
        User user = repository.findByUsername(username);
        return user;
    }

}
