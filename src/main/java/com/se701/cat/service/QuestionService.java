package com.se701.cat.service;
import com.se701.cat.entity.Question;
import com.se701.cat.respository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository repository;

    public void createQuestion(String questionId, double difficultyParameter, String questionType, String questionContent, List<Question.Option> options){
        Question newQuestion = new Question(questionId, difficultyParameter,questionType, questionContent, null, options);
        repository.save(newQuestion);
    }

    public Question findQuestion(String questionId){
        return repository.findById(questionId).get();
    }

    public List<Question> getFixedLengthQuestions() {
        return repository.findAllByModuleNumber(null);
    }

    public List<Question> getMultistageQuestions(Integer moduleNumber) {
        return repository.findAllByModuleNumber(moduleNumber);
    }
}
