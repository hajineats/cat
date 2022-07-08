package com.se701.cat.controller;

import com.se701.cat.dto.TestResponseDTO;
import com.se701.cat.entity.Question;
import com.se701.cat.entity.User;
import com.se701.cat.service.QuestionService;
import com.se701.cat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("fixed")
public class FixedLengthTestController {
    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseEntity<List<Question>> getQuestions() {
        return ResponseEntity.ok(questionService.findAllFixedLengthQuestions());
    }

    @PostMapping()
    public ResponseEntity submitResults(@RequestBody TestResponseDTO testResponseDTO) {
        User user = userService.findUserById(testResponseDTO.getUserId());
        if (user != null) {
            userService.addUserResponses(false, testResponseDTO.getResponses(), user);
            user.getShouldTakes().remove(0);
            userService.updateUser(user);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
