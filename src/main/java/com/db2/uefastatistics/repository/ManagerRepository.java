package com.db2.uefastatistics.repository;

import com.db2.uefastatistics.model.Manager;
import com.db2.uefastatistics.model.Team;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends MongoRepository<Manager, String> {

    List<Manager> findByNationality(String nationality);

    List<Manager> findByTeamId(ObjectId teamId);

}
