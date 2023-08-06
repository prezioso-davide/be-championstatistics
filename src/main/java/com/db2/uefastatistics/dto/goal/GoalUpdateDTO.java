package com.db2.uefastatistics.dto.goal;

import lombok.Data;

@Data
public class GoalUpdateDTO {

    int minute;
    String description;
    String playerId;
    String assist;
    String matchId;

}
