package com.db2.uefastatistics.repository;

import com.db2.uefastatistics.model.Team;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends MongoRepository<Team, String> {

    List<Team> findByCountry(String country);
    List<Team> findByStadiumId(ObjectId stadiumId);

}
