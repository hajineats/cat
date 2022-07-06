package com.se701.cat.controller;

import com.se701.cat.dto.QuestionListDTO;
import com.se701.cat.entity.Question;
import com.se701.cat.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("questions")
public class QuestionController {
    @Autowired
    QuestionService service;

    /**
     * Populates the Questions collection with a list of questions.
     * @param body DTO containing a list of questions
     */
    @PostMapping
    public ResponseEntity populateQuestions(@RequestBody QuestionListDTO body){
        service.persistQuestions(body.toDomainObject());
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves a question of the specified id
     */
    @GetMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Question> getQuestion(@PathVariable String id){
        Question question = service.findQuestion(id);
        if (question == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(question);
    }

    /**
     * Retrieves all the questions
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Question>> getAllQuestions(){
        return ResponseEntity.ok(service.findAllQuestions());
    }

    /**
     * Deletes all the questions
     */
    @DeleteMapping
    public ResponseEntity deleteAllQuestions(){
        service.deleteAllQuestions();
        return ResponseEntity.ok().build();
    }
}
