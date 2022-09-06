package com.p4p.cat.respository;

import com.p4p.cat.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuestionRepository extends MongoRepository<Question, String> {
    public List<Question> findAllByModuleNumber(int moduleNumber);

    public List<Question> findAllByModuleNumberGreaterThanEqual(int moduleNumber);
    public List<Question> findAllByModuleNumberNotNull();
}
