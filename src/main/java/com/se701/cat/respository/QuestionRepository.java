package com.se701.cat.respository;

import com.se701.cat.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuestionRepository extends MongoRepository<Question, String> {
    public List<Question> findAllByModuleNumber(Integer moduleNumber);

    public List<Question> findAllByModuleNumberGreaterThanEqual(Integer moduleNumber);
    public List<Question> findAllByModuleNumberNotNull();
}
