package com.db2.uefastatistics.repository;

import com.db2.uefastatistics.model.Goal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends MongoRepository<Goal, String> {

}
