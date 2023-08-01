package com.db2.uefastatistics.repository;

import com.db2.uefastatistics.model.Stadium;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StadiumRepository extends MongoRepository<Stadium, String> {

    List<Stadium> findByCountry(String country);

}
