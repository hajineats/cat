package com.se701.cat.controller;

import com.se701.cat.entity.Question;
import com.se701.cat.entity.Question.Option;
import com.se701.cat.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class QuestionController {
    @Autowired
    QuestionService service;

    @PostMapping("/question")
    @ResponseBody
    public String createQuestion(){

        List<Option> options = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            Option option = new Option("OptionIdIs" + i, "sin(" + i * 17 + ")");
            options.add(option);
        }

        service.createQuestion("M123456", 0.5, "MST", "lol this is a question. what is a question?", options);
        return "success@";
    }

    @GetMapping(value = "/question", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Question getQuestion(){
        return service.findQuestion("M123456");
    }
}
