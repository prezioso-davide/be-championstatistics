package com.db2.uefastatistics.repository;

import com.db2.uefastatistics.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends MongoRepository<Team, String> {

}
