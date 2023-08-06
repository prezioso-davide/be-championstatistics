package com.db2.uefastatistics.controller;

import com.db2.uefastatistics.dto.goal.GoalCreationDTO;
import com.db2.uefastatistics.dto.goal.GoalUpdateDTO;
import com.db2.uefastatistics.dto.goal.GoalViewDTO;
import com.db2.uefastatistics.service.goal.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("public/goal")
public class GoalController {
    
    @Autowired
    public GoalService goalService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<GoalViewDTO>> getAllGoals() {
        List<GoalViewDTO> listDTO = goalService.getAllGoals();
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/allByPlayerId/{playerId}")
    public ResponseEntity<List<GoalViewDTO>> getGoalsByPlayerId(@PathVariable(required = true, name = "playerId") String playerId) {
        List<GoalViewDTO> listDTO = goalService.getGoalsByPlayerId(playerId);
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/allByAssist/{assist}")
    public ResponseEntity<List<GoalViewDTO>> getGoalsByAssist(@PathVariable(required = true, name = "assist") String assist) {
        List<GoalViewDTO> listDTO = goalService.getGoalsByAssist(assist);
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/allByMatchId/{matchId}")
    public ResponseEntity<List<GoalViewDTO>> getGoalsByMatchId(@PathVariable(required = true, name = "matchId") String matchId) {
        List<GoalViewDTO> listDTO = goalService.getGoalsByMatchId(matchId);
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GoalViewDTO> getGoalById(@PathVariable(required = true, name = "id") String id) {
        GoalViewDTO goalDTO = goalService.getGoalById(id);
        return new ResponseEntity<>(goalDTO, HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<GoalViewDTO> createGoal(@RequestBody GoalCreationDTO goalCreationDTO) {
        GoalViewDTO goalDTO = goalService.createGoal(goalCreationDTO);
        return new ResponseEntity<>(goalDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<GoalViewDTO> updateGoal(
            @RequestBody GoalUpdateDTO goalUpdateDTO,
            @PathVariable(required = true, name = "id") String id
    ) {
        GoalViewDTO goalDTO = goalService.updateGoal(goalUpdateDTO, id);
        return new ResponseEntity<>(goalDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable(required = true, name = "id") String id) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }
    
}
