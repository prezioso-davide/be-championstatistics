package com.db2.uefastatistics.service.match;

import com.db2.uefastatistics.dto.match.MatchCreationDTO;
import com.db2.uefastatistics.dto.match.MatchUpdateDTO;
import com.db2.uefastatistics.dto.match.MatchViewDTO;
import com.db2.uefastatistics.model.Match;
import com.db2.uefastatistics.model.Stadium;
import com.db2.uefastatistics.model.Team;
import com.db2.uefastatistics.repository.MatchRepository;
import com.db2.uefastatistics.repository.StadiumRepository;
import com.db2.uefastatistics.repository.TeamRepository;
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

    @Override
    public List<MatchViewDTO> getAllMatches() {
        List<Match> list = matchRepository.findAll();
        List<MatchViewDTO> listDTO = new ArrayList<>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessuna partita trovata");
        }

        for (Match p: list) {
            listDTO.add(entityToDTO(p));
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

        for (Match p: list) {
            listDTO.add(entityToDTO(p));
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

        for (Match p: list) {
            listDTO.add(entityToDTO(p));
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

        for (Match p: list) {
            listDTO.add(entityToDTO(p));
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

        for (Match p: list) {
            listDTO.add(entityToDTO(p));
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
            matchDTO.setHomeTeam(match.getHomeTeam().toString());
        }

        if(match.getAwayTeam() != null){
            matchDTO.setAwayTeam(match.getAwayTeam().toString());
        }

        if(match.getStadiumId() != null){
            matchDTO.setStadiumId(match.getStadiumId().toString());
        }

        return matchDTO;
    }
    
}
