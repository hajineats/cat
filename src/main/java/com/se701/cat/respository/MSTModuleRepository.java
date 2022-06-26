package com.se701.cat.respository;

import com.se701.cat.entity.MSTModule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MSTModuleRepository extends MongoRepository<MSTModule, String> {
}
