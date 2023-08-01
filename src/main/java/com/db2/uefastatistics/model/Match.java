package com.db2.uefastatistics.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "matches")
public class Match {

    @Id
    private String id;
    private String season;
    private Date datetime;
    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private int penalityShootOut;
    private int attendance;
    private String stadiumId;

}
