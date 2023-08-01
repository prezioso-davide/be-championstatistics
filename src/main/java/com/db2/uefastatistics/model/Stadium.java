package com.db2.uefastatistics.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "stadium")
public class Stadium {

    @Id
    private String id;
    @Field(name = "stadium_name")
    private String stadiumName;
    private String city;
    private String country;
    private int capacity;
    private double latitude;
    private double longitude;

}
