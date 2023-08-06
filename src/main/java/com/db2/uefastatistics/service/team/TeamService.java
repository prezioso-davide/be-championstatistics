package com.db2.uefastatistics.service.team;

import com.db2.uefastatistics.dto.team.TeamCreationDTO;
import com.db2.uefastatistics.dto.team.TeamUpdateDTO;
import com.db2.uefastatistics.dto.team.TeamViewDTO;

import java.util.List;

public interface TeamService {

    List<TeamViewDTO> getAllTeams();
    TeamViewDTO getTeamById(String id);
    List<TeamViewDTO> getTeamsByCountry(String country);
    List<TeamViewDTO> getTeamsByStadiumId(String stadiumId);
    TeamViewDTO createTeam(TeamCreationDTO creationDTO);
    TeamViewDTO updateTeam(TeamUpdateDTO updateDTO, String id);
    void deleteTeam(String id);

}
