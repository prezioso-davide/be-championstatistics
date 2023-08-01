package com.db2.uefastatistics.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "players")
public class Player {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String nationality;
    private Date birthday;
    private int jersey_number;
    private String position;
    private double height;
    private int weight;
    private String foot;
    private String teamId;

}
