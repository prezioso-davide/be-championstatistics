package com.db2.uefastatistics.dto.goal;

import lombok.Data;

@Data
public class GoalViewDTO {

    String id;
    int minute;
    String description;
    String playerId;
    String assist;
    String matchId;

}
