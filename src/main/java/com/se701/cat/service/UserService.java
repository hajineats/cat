package com.se701.cat.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.se701.cat.MongoConfig;
import com.se701.cat.entity.User;
import com.se701.cat.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    @Autowired
    MongoConfig mongoConfig;

    public void createUser(String name){
        User user = new User(name,  0, 0);
        repository.save(user);
    }


    public User findUser(String username){
        User user = repository.findByUsername(username);
        return user;
    }

    /**
     * @param isMST whether the responses are for MST
     * @param responsesToAdd map of questionId to answer option
     * @param user the user for which these responses are to be added
     * @return true if the update was successful
     */
    public boolean addUserResponses(boolean isMST, Map<String, String> responsesToAdd, User user){
        if (user==null) {
            return false;
        }
        for (Map.Entry<String, String> entry : responsesToAdd.entrySet()) {
            if(isMST){
                user.addMSTTestResponse(entry.getKey(), entry.getValue());
            }else{
                user.addFixedTestResponse(entry.getKey(), entry.getValue());
            }
        }
        repository.save(user);
        return true;
    }

}
