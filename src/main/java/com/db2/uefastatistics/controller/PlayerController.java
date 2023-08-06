package com.db2.uefastatistics.controller;

import com.db2.uefastatistics.dto.player.PlayerCreationDTO;
import com.db2.uefastatistics.dto.player.PlayerUpdateDTO;
import com.db2.uefastatistics.dto.player.PlayerViewDTO;
import com.db2.uefastatistics.service.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("public/player")
public class PlayerController {

    @Autowired
    public PlayerService playerService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<PlayerViewDTO>> getAllTeams() {
        List<PlayerViewDTO> listDTO = playerService.getAllPlayers();
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/allByNationality/{nationality}")
    public ResponseEntity<List<PlayerViewDTO>> getPlayersByNationality(@PathVariable(required = true, name = "nationality") String nationality) {
        List<PlayerViewDTO> listDTO = playerService.getPlayersByNationality(nationality);
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/allByTeamId/{teamId}")
    public ResponseEntity<List<PlayerViewDTO>> getPlayersByTeamId(@PathVariable(required = true, name = "teamId") String teamId) {
        List<PlayerViewDTO> listDTO = playerService.getPlayersByTeamId(teamId);
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PlayerViewDTO> getPlayerById(@PathVariable(required = true, name = "id") String id) {
        PlayerViewDTO playerDTO = playerService.getPlayerById(id);
        return new ResponseEntity<>(playerDTO, HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<PlayerViewDTO> createPlayer(@RequestBody PlayerCreationDTO playerCreationDTO) {
        PlayerViewDTO playerDTO = playerService.createPlayer(playerCreationDTO);
        return new ResponseEntity<>(playerDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<PlayerViewDTO> updatePlayer(
            @RequestBody PlayerUpdateDTO playerUpdateDTO,
            @PathVariable(required = true, name = "id") String id
        ) {
        PlayerViewDTO playerDTO = playerService.updatePlayer(playerUpdateDTO, id);
        return new ResponseEntity<>(playerDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable(required = true, name = "id") String id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }

}
