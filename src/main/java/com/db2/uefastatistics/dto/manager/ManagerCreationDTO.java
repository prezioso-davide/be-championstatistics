package com.db2.uefastatistics.dto.manager;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ManagerCreationDTO {

    String firstName;
    String lastName;
    String nationality;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    Date birthday;
    String teamId;

}
