package com.se701.cat.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.se701.cat.MongoConfig;
import com.se701.cat.entity.TestType;
import com.se701.cat.entity.User;
import com.se701.cat.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    @Autowired
    MongoConfig mongoConfig;

    /**
     * Creates a user
     * @param id new user's id
     * @return true if successfully created, false if there's conflict or error
     */
    public boolean createUser(Long id) {
        if(repository.existsById(id)){
            return false;
        }
        User user = new User(id, 0, 0, List.of(TestType.FL, TestType.MST));
        repository.save(user);
        return true;
    }

    public void updateUser(User user) {
        repository.save(user);
    }

    public User findUserById(Long id) {
        Optional<User> userOptional = repository.findById(id);
        return userOptional.isEmpty() ? null : userOptional.get();
    }

    /**
     * @param isMST          whether the responses are for MST
     * @param responsesToAdd map of questionId to answer option
     * @param user           the user for which these responses are to be added
     * @return true if the update was successful
     */
    public boolean addUserResponses(boolean isMST, Map<String, String> responsesToAdd, User user) {
        if (user == null) {
            return false;
        }
        for (Map.Entry<String, String> entry : responsesToAdd.entrySet()) {
            if (isMST) {
                user.addMSTTestResponse(entry.getKey(), entry.getValue());
            } else {
                user.addFixedTestResponse(entry.getKey(), entry.getValue());
            }
        }
        repository.save(user);
        return true;
    }

}
