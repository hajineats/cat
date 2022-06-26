package com.se701.cat.controller;

import com.se701.cat.dto.TestResponseDTO;
import com.se701.cat.entity.Question;
import com.se701.cat.entity.User;
import com.se701.cat.service.QuestionService;
import com.se701.cat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@CrossOrigin
@Controller
public class MultistageTestController {
    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;

    @GetMapping("/multistage/{moduleId}")
    public ResponseEntity<List<Question>> getModuleQuestions(@PathVariable Integer moduleId) {
        return ResponseEntity.ok(questionService.getMultistageQuestions(moduleId));
    }

    /**
     *
     * @param testResponseDTO
     * @return the number of the next module or null if the test is complete
     */
    @PostMapping("/multistage")
    public ResponseEntity<Integer> submitResults(TestResponseDTO testResponseDTO) {
        User user = userService.findUserById(testResponseDTO.getUserId());
        if (user != null) {
            userService.addUserResponses(true, testResponseDTO.getResponses(), user);
            // TODO
        }
        return ResponseEntity.notFound().build();
    }

}
