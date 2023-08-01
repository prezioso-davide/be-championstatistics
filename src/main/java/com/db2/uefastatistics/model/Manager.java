package com.db2.uefastatistics.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "managers")
public class Manager {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String nationality;
    private Date birthday;
    private String teamId;

}
