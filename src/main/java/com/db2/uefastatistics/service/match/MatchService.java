package com.db2.uefastatistics.service.match;

import com.db2.uefastatistics.dto.match.MatchCreationDTO;
import com.db2.uefastatistics.dto.match.MatchUpdateDTO;
import com.db2.uefastatistics.dto.match.MatchViewDTO;

import java.util.List;

public interface MatchService {

    List<MatchViewDTO> getAllMatches();
    MatchViewDTO getMatchById(String id);
    List<MatchViewDTO> getMatchesBySeason(String season);
    List<MatchViewDTO> getMatchesByHomeTeam(String homeTeamId);
    List<MatchViewDTO> getMatchesByAwayTeam(String awayTeamId);
    List<MatchViewDTO> getMatchesByStadiumId(String stadiumId);
    MatchViewDTO createMatch(MatchCreationDTO creationDTO);
    MatchViewDTO updateMatch(MatchUpdateDTO updateDTO, String id);
    void deleteMatch(String id);
    
}
