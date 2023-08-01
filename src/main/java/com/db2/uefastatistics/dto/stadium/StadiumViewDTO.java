package com.db2.uefastatistics.dto.stadium;

import lombok.Data;

@Data
public class StadiumViewDTO {

    String id;
    String stadiumName;
    String city;
    String country;
    int capacity;
    double latitude;
    double longitude;

}
