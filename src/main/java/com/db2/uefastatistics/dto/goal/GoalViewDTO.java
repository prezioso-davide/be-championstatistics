package com.db2.uefastatistics.dto.goal;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class GoalViewDTO {

    String id;
    int minute;
    String description;
    String playerId;
    String playerFullName;
    String assist;
    String assistFullName;
    String matchId;

}
