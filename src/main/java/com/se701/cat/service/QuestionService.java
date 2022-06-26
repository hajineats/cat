package com.se701.cat.service;
import com.se701.cat.entity.Question;
import com.se701.cat.respository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository repository;

    public void createQuestion(String questionId, double difficultyParameter, String questionType, String questionContent, Map<String, String> options){
        Question newQuestion = new Question(questionId, difficultyParameter,questionType, questionContent);
        for (Map.Entry<String, String> option : options.entrySet()) {
            newQuestion.addOption(option.getKey(), option.getValue());
        }

        repository.save(newQuestion);
    }

    public Question findQuestion(String questionId){
        return repository.findQuestionByQuestionId(questionId);
    }
}
