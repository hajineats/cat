package com.se701.cat.respository;

import com.se701.cat.entity.FixedLengthTest;
import com.se701.cat.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FixedLengthTestRepository extends MongoRepository<FixedLengthTest, String> {
}
