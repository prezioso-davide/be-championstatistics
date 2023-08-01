package com.db2.uefastatistics.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "teams")
public class Team {

    @Id
    private String id;
    private String teamName;
    private String country;
    private String stadiumId;

}
