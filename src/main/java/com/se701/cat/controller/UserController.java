package com.se701.cat.controller;

import com.se701.cat.entity.User;
import com.se701.cat.respository.UserRepository;
import com.se701.cat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@CrossOrigin
@Controller
public class UserController {
    @Autowired UserService userService;

    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody Long userId){
        if(userService.createUser(userId)){
            return ResponseEntity.created(URI.create("/user/"+userId)).build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }
}
