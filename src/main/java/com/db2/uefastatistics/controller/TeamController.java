package com.db2.uefastatistics.controller;

import com.db2.uefastatistics.dto.team.TeamCreationDTO;
import com.db2.uefastatistics.dto.team.TeamUpdateDTO;
import com.db2.uefastatistics.dto.team.TeamViewDTO;
import com.db2.uefastatistics.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("public/team")
public class TeamController {

    @Autowired
    public TeamService teamService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<TeamViewDTO>> getAllTeams() {
        List<TeamViewDTO> listDTO = teamService.getAllTeams();
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/allByCountry/{country}")
    public ResponseEntity<List<TeamViewDTO>> getTeamsByCountry(@PathVariable(required = true, name = "country") String country) {
        List<TeamViewDTO> listDTO = teamService.getTeamsByCountry(country);
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/allByStadiumId/{stadiumId}")
    public ResponseEntity<List<TeamViewDTO>> getTeamsByStadiumId(@PathVariable(required = true, name = "stadiumId") String stadiumId) {
        List<TeamViewDTO> listDTO = teamService.getTeamsByStadiumId(stadiumId);
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TeamViewDTO> getTeamById(@PathVariable(required = true, name = "id") String id) {
        TeamViewDTO teamDTO = teamService.getTeamById(id);
        return new ResponseEntity<>(teamDTO, HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<TeamViewDTO> createTeam(@RequestBody TeamCreationDTO teamCreationDTO) {
        TeamViewDTO teamDTO = teamService.createTeam(teamCreationDTO);
        return new ResponseEntity<>(teamDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<TeamViewDTO> updateTeam(
            @RequestBody TeamUpdateDTO teamUpdateDTO,
            @PathVariable(required = true, name = "id") String id
        ) {
        TeamViewDTO teamDTO = teamService.updateTeam(teamUpdateDTO, id);
        return new ResponseEntity<>(teamDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable(required = true, name = "id") String id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }

}
