package com.db2.uefastatistics.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Document(collection = "players")
public class Player {

    @Id
    private String id;
    @Field(name = "first_name")
    private String firstName;
    @Field(name = "last_name")
    private String lastName;
    private String nationality;
    private Date birthday;
    @Field(name = "jersey_number")
    private int jerseyNumber;
    private String position;
    private double height;
    private int weight;
    private String foot;
    @Field(name = "team_id")
    private ObjectId teamId;

}
