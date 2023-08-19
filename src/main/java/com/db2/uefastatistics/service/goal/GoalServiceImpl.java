package com.db2.uefastatistics.service.goal;

import com.db2.uefastatistics.dto.goal.GoalCreationDTO;
import com.db2.uefastatistics.dto.goal.GoalUpdateDTO;
import com.db2.uefastatistics.dto.goal.GoalViewDTO;
import com.db2.uefastatistics.model.Goal;
import com.db2.uefastatistics.model.Match;
import com.db2.uefastatistics.model.Player;
import com.db2.uefastatistics.repository.GoalRepository;
import com.db2.uefastatistics.repository.MatchRepository;
import com.db2.uefastatistics.repository.PlayerRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GoalServiceImpl implements GoalService{

    @Autowired
    public GoalRepository goalRepository;

    @Autowired
    public PlayerRepository playerRepository;
    
    @Autowired
    public MatchRepository matchRepository;

    @Override
    public List<GoalViewDTO> getAllGoals() {
        List<Goal> list = goalRepository.findAll();
        List<GoalViewDTO> listDTO = new ArrayList<>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessun goal trovato");
        }

        for (Goal p: list) {
            listDTO.add(entityToDTO(p));
        }

        return listDTO;
    }

    @Override
    public GoalViewDTO getGoalById(String id) {
        Optional<Goal> goalOPT = goalRepository.findById(id);
        GoalViewDTO goalDTO;

        if(goalOPT.isPresent()) {
            goalDTO = entityToDTO(goalOPT.get());
        } else {
            throw new RuntimeException("Goal non trovato");
        }

        return goalDTO;
    }

    @Override
    public List<GoalViewDTO> getGoalsByPlayerId(String playerId) {
        ObjectId objectId = new ObjectId(playerId);
        List<Goal> list = goalRepository.findByPlayerId(objectId);
        List<GoalViewDTO> listDTO = new ArrayList<>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessun goal trovato");
        }

        for (Goal p: list) {
            listDTO.add(entityToDTO(p));
        }

        return listDTO;
    }

    @Override
    public List<GoalViewDTO> getGoalsByAssist(String assist) {
        ObjectId objectId = new ObjectId(assist);
        List<Goal> list = goalRepository.findByAssist(objectId);
        List<GoalViewDTO> listDTO = new ArrayList<>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessun goal trovato");
        }

        for (Goal p: list) {
            listDTO.add(entityToDTO(p));
        }

        return listDTO;
    }

    @Override
    public List<GoalViewDTO> getGoalsByMatchId(String matchId) {
        ObjectId objectId = new ObjectId(matchId);
        List<GoalViewDTO> listDTO = new ArrayList<>();

        try {
            List<Goal> list = goalRepository.findByMatchId(objectId);

            for (Goal p: list) {
                listDTO.add(entityToDTO(p));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listDTO;
    }

    @Override
    public GoalViewDTO createGoal(GoalCreationDTO createDTO) {
        Goal goal = createGoalConstructor(createDTO);

        goal = goalRepository.save(goal);

        return entityToDTO(goal);
    }

    @Override
    public GoalViewDTO updateGoal(GoalUpdateDTO updateDTO, String id) {
        Optional<Goal> goalOPT = goalRepository.findById(id);
        Goal goal = new Goal();

        if(goalOPT.isPresent()) {
            goal = updateGoalConstructor(updateDTO, goalOPT.get());
        } else {
            throw new RuntimeException("Nessun goal trovato");
        }

        goal = goalRepository.save(goal);

        return entityToDTO(goal);
    }

    @Override
    public void deleteGoal(String id) {
        goalRepository.deleteById(id);
    }

    private Goal createGoalConstructor(GoalCreationDTO createDTO) {
        Goal goal = new Goal();

        goal.setMinute(createDTO.getMinute());
        goal.setDescription(createDTO.getDescription());

        ObjectId playerId = new ObjectId(createDTO.getPlayerId());
        Optional<Player> playerOPT = playerRepository.findById(createDTO.getPlayerId());
        if (playerOPT.isPresent()){
            goal.setPlayerId(playerId);
        } else {
            throw new RuntimeException("Nessun marcatore trovato");
        }

        if(createDTO.getAssist() != null) {
            ObjectId assist = new ObjectId(createDTO.getAssist());
            Optional<Player> assistOPT = playerRepository.findById(createDTO.getAssist());
            if (assistOPT.isPresent()){
                goal.setAssist(assist);
            }
        }

        ObjectId matchId = new ObjectId(createDTO.getMatchId());
        Optional<Match> matchOPT = matchRepository.findById(createDTO.getMatchId());
        if (matchOPT.isPresent()){
            goal.setMatchId(matchId);
        } else {
            throw new RuntimeException("Nessuna partita trovata");
        }

        return goal;
    }

    private Goal updateGoalConstructor(GoalUpdateDTO updateDTO, Goal goal) {
        goal.setMinute(updateDTO.getMinute());
        goal.setDescription(updateDTO.getDescription());

        ObjectId playerId = new ObjectId(updateDTO.getPlayerId());
        Optional<Player> playerOPT = playerRepository.findById(updateDTO.getPlayerId());
        if (playerOPT.isPresent()){
            goal.setPlayerId(playerId);
        } else {
            throw new RuntimeException("Nessun marcatore trovato");
        }

        if(updateDTO.getAssist() != null) {
            ObjectId assist = new ObjectId(updateDTO.getAssist());
            Optional<Player> assistOPT = playerRepository.findById(updateDTO.getAssist());
            if (assistOPT.isPresent()) {
                goal.setAssist(assist);
            }
        }

        ObjectId matchId = new ObjectId(updateDTO.getMatchId());
        Optional<Match> matchOPT = matchRepository.findById(updateDTO.getMatchId());
        if (matchOPT.isPresent()){
            goal.setMatchId(matchId);
        } else {
            throw new RuntimeException("Nessuna partita trovata");
        }

        return goal;
    }

    private GoalViewDTO entityToDTO(Goal goal) {
        GoalViewDTO goalDTO = new GoalViewDTO();

        goalDTO.setId(goal.getId());
        goalDTO.setMinute(goal.getMinute());
        goalDTO.setDescription(goal.getDescription());

        if(goal.getPlayerId() != null){
            Optional<Player> player = playerRepository.findById(goal.getPlayerId().toString());
            goalDTO.setPlayerId(goal.getPlayerId().toString());
            if(player.get().getFirstName() != null){
                goalDTO.setPlayerFullName(player.get().getFirstName() + " " + player.get().getLastName());
            } else {
                goalDTO.setPlayerFullName(player.get().getLastName());
            }
        }

        if(goal.getAssist() != null){
            Optional<Player> assist = playerRepository.findById(goal.getAssist().toString());
            goalDTO.setAssist(goal.getAssist().toString());
            if(assist.get().getFirstName() != null){
                goalDTO.setAssistFullName(assist.get().getFirstName() + " " + assist.get().getLastName());
            } else {
                goalDTO.setAssistFullName(assist.get().getLastName());
            }
        }

        if(goal.getMatchId() != null){
            goalDTO.setMatchId(goal.getMatchId().toString());
        }

        return goalDTO;
    }

}
