package com.se701.cat.respository;

import com.se701.cat.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question, String> {
    public Question findQuestionByQuestionId(String questionId);
}
