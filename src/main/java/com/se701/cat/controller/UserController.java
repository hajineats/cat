package com.se701.cat.controller;

import com.se701.cat.entity.User;
import com.se701.cat.respository.UserRepository;
import com.se701.cat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@Controller
public class UserController {
    @Autowired UserService userService;

    @PostMapping("/user")
    @ResponseBody
    public String createUser(){
        userService.createUser("Jason");
        return "successful";
    }

    @GetMapping("/user")
    @ResponseBody
    public User getUser(){
        User user = userService.findUser("Jason");
        return user;
    }
}
