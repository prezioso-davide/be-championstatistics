package com.db2.uefastatistics.repository;

import com.db2.uefastatistics.model.Player;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {

    List<Player> findByNationality(String nationality);

    List<Player> findByTeamId(ObjectId teamId);

}
