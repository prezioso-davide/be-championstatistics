package com.db2.uefastatistics.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Document(collection = "matches")
public class Match {

    @Id
    private String id;
    private String season;
    private Date datetime;
    private int attendance;
    @Field(name = "home_team_score")
    private int homeTeamScore;
    @Field(name = "away_team_score")
    private int awayTeamScore;
    @Field(name = "penality_shoot_out")
    private int penalityShootOut;
    @Field(name = "home_team")
    private ObjectId homeTeam;
    @Field(name = "away_team")
    private ObjectId awayTeam;
    @Field(name = "stadium_id")
    private ObjectId stadiumId;

}
