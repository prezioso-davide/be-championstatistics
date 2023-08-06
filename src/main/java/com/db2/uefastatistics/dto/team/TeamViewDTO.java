package com.db2.uefastatistics.dto.team;

import com.db2.uefastatistics.dto.stadium.StadiumViewDTO;
import lombok.Data;

@Data
public class TeamViewDTO {

    String id;
    String teamName;
    String country;
    String stadiumId;

}
