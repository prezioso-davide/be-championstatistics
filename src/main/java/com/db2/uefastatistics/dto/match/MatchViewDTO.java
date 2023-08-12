package com.db2.uefastatistics.dto.match;

import com.db2.uefastatistics.dto.goal.GoalViewDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MatchViewDTO {

    String id;
    String season;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    Date datetime;
    int attendance;
    int homeTeamScore;
    int awayTeamScore;
    int penalityShootOut;
    String homeTeam;
    String homeTeamName;
    String awayTeam;
    String awayTeamName;
    String stadiumId;
    String stadiumName;
    List<GoalViewDTO> goals;

}
