package com.db2.uefastatistics.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "teams")
public class Team {

    @Id
    private String id;
    @Field(name = "team_name")
    private String teamName;
    private String country;
    @Field(name = "stadium_id")
    private ObjectId stadiumId;

}
