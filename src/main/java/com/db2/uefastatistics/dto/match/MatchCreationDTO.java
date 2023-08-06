package com.db2.uefastatistics.dto.match;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class MatchCreationDTO {

    String season;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    Date datetime;
    int attendance;
    int homeTeamScore;
    int awayTeamScore;
    int penalityShootOut;
    String homeTeam;
    String awayTeam;
    String stadiumId;

}
