package com.db2.uefastatistics.service.goal;

import com.db2.uefastatistics.dto.goal.GoalCreationDTO;
import com.db2.uefastatistics.dto.goal.GoalUpdateDTO;
import com.db2.uefastatistics.dto.goal.GoalViewDTO;

import java.util.List;

public interface GoalService {

    List<GoalViewDTO> getAllGoals();
    GoalViewDTO getGoalById(String id);
    List<GoalViewDTO> getGoalsByPlayerId(String playerId);
    List<GoalViewDTO> getGoalsByAssist(String assist);
    List<GoalViewDTO> getGoalsByMatchId(String matchId);
    GoalViewDTO createGoal(GoalCreationDTO creationDTO);
    GoalViewDTO updateGoal(GoalUpdateDTO updateDTO, String id);
    void deleteGoal(String id);
    
}
