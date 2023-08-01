package com.db2.uefastatistics.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "goals")
public class Goal {

    @Id
    private String id;
    private String matchId;
    private String playerId;
    private int minute;
    private String assist;
    private String description;
}
