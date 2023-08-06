package com.db2.uefastatistics.repository;

import com.db2.uefastatistics.model.Goal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends MongoRepository<Goal, String> {

    List<Goal> findByPlayerId(ObjectId playerId);

    List<Goal> findByAssist(ObjectId assist);

    List<Goal> findByMatchId(ObjectId matchId);

}
