package com.se701.cat.controller;

import com.se701.cat.dto.CreateUserDTO;
import com.se701.cat.entity.User;
import com.se701.cat.respository.UserRepository;
import com.se701.cat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired UserService userService;


    /**
     * Batch create users
     * @param
     * @return
     */
    @PostMapping()
    public ResponseEntity<List<Long>> createUsers(@RequestBody CreateUserDTO body){
        List<Long> assignedIds = new ArrayList<>();
        for(int i = 0; i < body.getNumOfUsers(); i++){
            userService.createUser((long) i, i%2==0);
            assignedIds.add((long)i);
        }
        return ResponseEntity.ok(assignedIds);
    }

    @DeleteMapping()
    public ResponseEntity deleteAllUsers(){
        userService.deleteAllUsers();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }
}
