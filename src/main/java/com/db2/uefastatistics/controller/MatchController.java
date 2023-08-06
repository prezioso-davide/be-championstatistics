package com.db2.uefastatistics.controller;

import com.db2.uefastatistics.dto.match.MatchCreationDTO;
import com.db2.uefastatistics.dto.match.MatchUpdateDTO;
import com.db2.uefastatistics.dto.match.MatchViewDTO;
import com.db2.uefastatistics.service.match.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("public/match")
public class MatchController {
    
    @Autowired
    public MatchService matchService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<MatchViewDTO>> getAllMatches() {
        List<MatchViewDTO> listDTO = matchService.getAllMatches();
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/allBySeason/{season}")
    public ResponseEntity<List<MatchViewDTO>> getMatchesBySeason(@PathVariable(required = true, name = "season") String season) {
        List<MatchViewDTO> listDTO = matchService.getMatchesBySeason(season);
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/allByHomeTeam/{homeTeam}")
    public ResponseEntity<List<MatchViewDTO>> getMatchesByHomeTeam(@PathVariable(required = true, name = "homeTeam") String homeTeam) {
        List<MatchViewDTO> listDTO = matchService.getMatchesByHomeTeam(homeTeam);
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/allByAwayTeam/{awayTeam}")
    public ResponseEntity<List<MatchViewDTO>> getMatchesByAwayTeam(@PathVariable(required = true, name = "awayTeam") String awayTeam) {
        List<MatchViewDTO> listDTO = matchService.getMatchesByAwayTeam(awayTeam);
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/allByStadiumId/{stadiumId}")
    public ResponseEntity<List<MatchViewDTO>> getMatchesByStadiumId(@PathVariable(required = true, name = "stadiumId") String stadiumId) {
        List<MatchViewDTO> listDTO = matchService.getMatchesByStadiumId(stadiumId);
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MatchViewDTO> getMatchById(@PathVariable(required = true, name = "id") String id) {
        MatchViewDTO matchDTO = matchService.getMatchById(id);
        return new ResponseEntity<>(matchDTO, HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<MatchViewDTO> createMatch(@RequestBody MatchCreationDTO matchCreationDTO) {
        MatchViewDTO matchDTO = matchService.createMatch(matchCreationDTO);
        return new ResponseEntity<>(matchDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<MatchViewDTO> updateMatch(
            @RequestBody MatchUpdateDTO matchUpdateDTO,
            @PathVariable(required = true, name = "id") String id
    ) {
        MatchViewDTO matchDTO = matchService.updateMatch(matchUpdateDTO, id);
        return new ResponseEntity<>(matchDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable(required = true, name = "id") String id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
    
}
