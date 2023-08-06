package com.db2.uefastatistics.controller;

import com.db2.uefastatistics.dto.stadium.StadiumCreationDTO;
import com.db2.uefastatistics.dto.stadium.StadiumUpdateDTO;
import com.db2.uefastatistics.dto.stadium.StadiumViewDTO;
import com.db2.uefastatistics.service.stadium.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("public/stadium")
public class StadiumController {

    @Autowired
    public StadiumService stadiumService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<StadiumViewDTO>> getAllStadiums() {
        List<StadiumViewDTO> listDTO = stadiumService.getAllStadiums();
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/allByCountry/{country}")
    public ResponseEntity<List<StadiumViewDTO>> getStadiumsByCountry(@PathVariable(required = true, name = "country") String country) {
        List<StadiumViewDTO> listDTO = stadiumService.getStadiumsByCountry(country);
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StadiumViewDTO> getStadiumById(@PathVariable(required = true, name = "id") String id) {
        StadiumViewDTO stadiumDTO = stadiumService.getStadiumById(id);
        return new ResponseEntity<>(stadiumDTO, HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<StadiumViewDTO> createStadium(@RequestBody StadiumCreationDTO stadiumCreationDTO) {
        StadiumViewDTO stadiumDTO = stadiumService.createStadium(stadiumCreationDTO);
        return new ResponseEntity<>(stadiumDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<StadiumViewDTO> updateStadium(
            @RequestBody StadiumUpdateDTO stadiumUpdateDTO,
            @PathVariable(required = true, name = "id") String id
        ) {
        StadiumViewDTO stadiumDTO = stadiumService.updateStadium(stadiumUpdateDTO, id);
        return new ResponseEntity<>(stadiumDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteStadium(@PathVariable(required = true, name = "id") String id) {
        stadiumService.deleteStadium(id);
        return ResponseEntity.noContent().build();
    }

}
