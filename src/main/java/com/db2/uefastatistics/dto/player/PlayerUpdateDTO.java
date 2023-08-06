package com.db2.uefastatistics.dto.player;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PlayerUpdateDTO {

    String firstName;
    String lastName;
    String nationality;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    Date birthday;
    int jerseyNumber;
    String position;
    double height;
    int weight;
    String foot;
    String teamId;

}
