package com.db2.uefastatistics.repository;

import com.db2.uefastatistics.model.Match;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends MongoRepository<Match, String> {

    List<Match> findBySeason(String season);

    List<Match> findByHomeTeam(ObjectId homeTeamId);

    List<Match> findByAwayTeam(ObjectId awayTeamId);

    List<Match> findByStadiumId(ObjectId stadiumId);

}
