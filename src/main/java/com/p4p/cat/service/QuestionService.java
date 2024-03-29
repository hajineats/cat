package com.p4p.cat.service;
import com.p4p.cat.respository.QuestionRepository;
import com.p4p.cat.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository repository;

//    public void createQuestion(String questionId, double difficultyParameter, String questionType, String questionContent, List<Question.Option> options, String correctAnswer){
//        Question newQuestion = new Question(questionId, difficultyParameter,questionType, questionContent, null, options, correctAnswer);
//        repository.save(newQuestion);
//    }

    public void persistQuestions(List<Question> questions){
        for (Question question : questions) {
            repository.save(question);
        }
    }

    /**
     * Retrieve question by id
     * @param id
     * @return null if question isn't present, otherwise a question object
     */
    public Question findQuestion(String id){
        if (id == null) {
            return null;
        }
        Optional<Question> question = repository.findById(id);
        if (question.isEmpty()) {
            return null;
        }
        return question.get();
    }

    public List<Question> findAllQuestions(){
        return repository.findAll();
    }

    public List<Question> findAllFixedLengthQuestions() {
        return repository.findAllByModuleNumber(Question.FIXED_LENGTH_MODULE_NUMBER);
    }

    public List<Question> findAllMultistageQuestions()  {
        return repository.findAllByModuleNumberGreaterThanEqual(0);
    }

    public List<Question> findAllMultistageQuestionsByModuleNumber(Integer moduleNumber) {
        return repository.findAllByModuleNumber(moduleNumber);
    }

    public void deleteAllQuestions() {
        repository.deleteAll();
    }
}
