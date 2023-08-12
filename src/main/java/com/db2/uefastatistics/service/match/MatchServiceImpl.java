package com.db2.uefastatistics.service.match;

import com.db2.uefastatistics.dto.goal.GoalViewDTO;
import com.db2.uefastatistics.dto.match.MatchCreationDTO;
import com.db2.uefastatistics.dto.match.MatchUpdateDTO;
import com.db2.uefastatistics.dto.match.MatchViewDTO;
import com.db2.uefastatistics.model.*;
import com.db2.uefastatistics.repository.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService{
    
    @Autowired
    public MatchRepository matchRepository;

    @Autowired
    public TeamRepository teamRepository;
    
    @Autowired
    public StadiumRepository stadiumRepository;

    @Autowired
    public GoalRepository goalRepository;

    @Autowired
    public PlayerRepository playerRepository;

    @Override
    public List<MatchViewDTO> getAllMatches() {
        List<Match> list = matchRepository.findAll();
        List<MatchViewDTO> listDTO = new ArrayList<>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessuna partita trovata");
        }

        for (Match m: list) {
            listDTO.add(entityToDTO(m));
        }

        return listDTO;
    }

    @Override
    public MatchViewDTO getMatchById(String id) {
        Optional<Match> matchOPT = matchRepository.findById(id);
        MatchViewDTO matchDTO;

        if(matchOPT.isPresent()) {
            matchDTO = entityToDTO(matchOPT.get());
        } else {
            throw new RuntimeException("Partita non trovata");
        }

        return matchDTO;
    }

    @Override
    public List<MatchViewDTO> getMatchesBySeason(String season) {
        List<Match> list = matchRepository.findBySeason(season);
        List<MatchViewDTO> listDTO = new ArrayList<>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessuna partita trovata");
        }

        for (Match m: list) {
            listDTO.add(entityToDTO(m));
        }

        return listDTO;
    }

    @Override
    public List<MatchViewDTO> getMatchesByHomeTeam(String homeTeam) {
        ObjectId objectId = new ObjectId(homeTeam);
        List<Match> list = matchRepository.findByHomeTeam(objectId);
        List<MatchViewDTO> listDTO = new ArrayList<>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessuna partita trovata");
        }

        for (Match m: list) {
            listDTO.add(entityToDTO(m));
        }

        return listDTO;
    }

    @Override
    public List<MatchViewDTO> getMatchesByAwayTeam(String awayTeam) {
        ObjectId objectId = new ObjectId(awayTeam);
        List<Match> list = matchRepository.findByAwayTeam(objectId);
        List<MatchViewDTO> listDTO = new ArrayList<>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessuna partita trovata");
        }

        for (Match m: list) {
            listDTO.add(entityToDTO(m));
        }

        return listDTO;
    }

    @Override
    public List<MatchViewDTO> getMatchesByStadiumId(String stadiumId) {
        ObjectId objectId = new ObjectId(stadiumId);
        List<Match> list = matchRepository.findByStadiumId(objectId);
        List<MatchViewDTO> listDTO = new ArrayList<>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessuna partita trovata");
        }

        for (Match m: list) {
            listDTO.add(entityToDTO(m));
        }

        return listDTO;
    }

    @Override
    public MatchViewDTO createMatch(MatchCreationDTO createDTO) {
        Match match = createMatchConstructor(createDTO);

        match = matchRepository.save(match);

        return entityToDTO(match);
    }

    @Override
    public MatchViewDTO updateMatch(MatchUpdateDTO updateDTO, String id) {
        Optional<Match> matchOPT = matchRepository.findById(id);
        Match match = new Match();

        if(matchOPT.isPresent()) {
            match = updateMatchConstructor(updateDTO, matchOPT.get());
        } else {
            throw new RuntimeException("Nessuna partita trovata");
        }

        match = matchRepository.save(match);

        return entityToDTO(match);
    }

    @Override
    public void deleteMatch(String id) {
        matchRepository.deleteById(id);
    }

    private Match createMatchConstructor(MatchCreationDTO createDTO) {
        Match match = new Match();

        match.setSeason(createDTO.getSeason());
        match.setDatetime(createDTO.getDatetime());
        match.setAttendance(createDTO.getAttendance());
        match.setHomeTeamScore(createDTO.getHomeTeamScore());
        match.setAwayTeamScore(createDTO.getAwayTeamScore());
        match.setPenalityShootOut(createDTO.getPenalityShootOut());

        ObjectId homeTeam = new ObjectId(createDTO.getHomeTeam());
        Optional<Team> homeTeamOPT = teamRepository.findById(createDTO.getHomeTeam());
        if (homeTeamOPT.isPresent()){
            match.setHomeTeam(homeTeam);
        } else {
            throw new RuntimeException("Nessuna squadra di casa trovata");
        }

        ObjectId awayTeam = new ObjectId(createDTO.getAwayTeam());
        Optional<Team> awayTeamOPT = teamRepository.findById(createDTO.getAwayTeam());
        if (awayTeamOPT.isPresent()){
            match.setAwayTeam(awayTeam);
        } else {
            throw new RuntimeException("Nessuna squadra ospite trovata");
        }

        ObjectId stadiumId = new ObjectId(createDTO.getStadiumId());
        Optional<Stadium> stadiumOPT = stadiumRepository.findById(createDTO.getStadiumId());
        if (stadiumOPT.isPresent()){
            match.setStadiumId(stadiumId);
        } else {
            throw new RuntimeException("Nessuno stadio trovato");
        }

        return match;
    }

    private Match updateMatchConstructor(MatchUpdateDTO updateDTO, Match match) {
        match.setSeason(updateDTO.getSeason());
        match.setDatetime(updateDTO.getDatetime());
        match.setAttendance(updateDTO.getAttendance());
        match.setHomeTeamScore(updateDTO.getHomeTeamScore());
        match.setAwayTeamScore(updateDTO.getAwayTeamScore());
        match.setPenalityShootOut(updateDTO.getPenalityShootOut());

        ObjectId homeTeam = new ObjectId(updateDTO.getHomeTeam());
        Optional<Team> homeTeamOPT = teamRepository.findById(updateDTO.getHomeTeam());
        if (homeTeamOPT.isPresent()){
            match.setHomeTeam(homeTeam);
        } else {
            throw new RuntimeException("Nessuna squadra di casa trovata");
        }

        ObjectId awayTeam = new ObjectId(updateDTO.getAwayTeam());
        Optional<Team> awayTeamOPT = teamRepository.findById(updateDTO.getAwayTeam());
        if (awayTeamOPT.isPresent()){
            match.setAwayTeam(awayTeam);
        } else {
            throw new RuntimeException("Nessuna squadra ospite trovata");
        }

        ObjectId stadiumId = new ObjectId(updateDTO.getStadiumId());
        Optional<Stadium> stadiumOPT = stadiumRepository.findById(updateDTO.getStadiumId());
        if (stadiumOPT.isPresent()){
            match.setStadiumId(stadiumId);
        } else {
            throw new RuntimeException("Nessuno stadio trovato");
        }

        return match;
    }

    private MatchViewDTO entityToDTO(Match match) {
        MatchViewDTO matchDTO = new MatchViewDTO();

        matchDTO.setId(match.getId());
        matchDTO.setSeason(match.getSeason());
        matchDTO.setDatetime(match.getDatetime());
        matchDTO.setAttendance(match.getAttendance());
        matchDTO.setHomeTeamScore(match.getHomeTeamScore());
        matchDTO.setAwayTeamScore(match.getAwayTeamScore());
        matchDTO.setPenalityShootOut(match.getPenalityShootOut());

        if(match.getHomeTeam() != null){
            Optional<Team> homeTeam = teamRepository.findById(match.getHomeTeam().toString());
            matchDTO.setHomeTeam(match.getHomeTeam().toString());
            matchDTO.setHomeTeamName(homeTeam.get().getTeamName());
        }

        if(match.getAwayTeam() != null){
            Optional<Team> awayTeam = teamRepository.findById(match.getAwayTeam().toString());
            matchDTO.setAwayTeam(match.getAwayTeam().toString());
            matchDTO.setAwayTeamName(awayTeam.get().getTeamName());
        }

        if(match.getStadiumId() != null){
            Optional<Stadium> stadium = stadiumRepository.findById(match.getStadiumId().toString());
            matchDTO.setStadiumId(match.getStadiumId().toString());
            matchDTO.setStadiumName(stadium.get().getStadiumName());
        }

        List<Goal> goalsMatch = goalRepository.findByMatchId(new ObjectId(match.getId()));
        if(goalsMatch != null) {
            List<GoalViewDTO> listDTO = listEntityToListDTO(goalsMatch);

            matchDTO.setGoals(listDTO);
        }

        return matchDTO;
    }

    private List<GoalViewDTO> listEntityToListDTO(List<Goal> goals) {
        List<GoalViewDTO> listDTO = new ArrayList<>();

        for (Goal g: goals) {
            GoalViewDTO goalDTO = new GoalViewDTO();
            goalDTO.setId(g.getId());
            goalDTO.setMinute(g.getMinute());
            goalDTO.setDescription(g.getDescription());

            if(g.getPlayerId() != null){
                Optional<Player> player = playerRepository.findById(g.getPlayerId().toString());
                goalDTO.setPlayerId(g.getPlayerId().toString());
                goalDTO.setPlayerFullName(player.get().getFirstName() + " " + player.get().getLastName());
            }

            if(g.getAssist() != null){
                Optional<Player> assist = playerRepository.findById(g.getAssist().toString());
                goalDTO.setAssist(g.getAssist().toString());
                goalDTO.setAssistFullName(assist.get().getFirstName() + " " + assist.get().getLastName());
            }

            if(g.getMatchId() != null){
                goalDTO.setMatchId(g.getMatchId().toString());
            }
            listDTO.add(goalDTO);
        }

        return listDTO;
    }
    
}
