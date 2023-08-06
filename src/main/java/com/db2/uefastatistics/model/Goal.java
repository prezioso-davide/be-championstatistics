package com.db2.uefastatistics.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "goals")
public class Goal {

    @Id
    private String id;
    private int minute;
    private String description;
    @Field(name = "player_id")
    private ObjectId playerId;
    private ObjectId assist;
    @Field(name = "match_id")
    private ObjectId matchId;

}
