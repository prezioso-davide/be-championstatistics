package com.db2.uefastatistics.dto.stadium;

import lombok.Data;

@Data
public class StadiumCreationDTO {

    String stadiumName;
    String city;
    String country;
    int capacity;
    double latitude;
    double longitude;

}
