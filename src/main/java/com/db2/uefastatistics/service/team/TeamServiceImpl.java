package com.db2.uefastatistics.service.team;

import com.db2.uefastatistics.dto.team.TeamCreationDTO;
import com.db2.uefastatistics.dto.team.TeamUpdateDTO;
import com.db2.uefastatistics.dto.team.TeamViewDTO;
import com.db2.uefastatistics.model.Stadium;
import com.db2.uefastatistics.model.Team;
import com.db2.uefastatistics.repository.StadiumRepository;
import com.db2.uefastatistics.repository.TeamRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private StadiumRepository stadiumRepository;

    @Override
    public List<TeamViewDTO> getAllTeams() {
        List<Team> list = teamRepository.findAll();
        List<TeamViewDTO> listDTO = new ArrayList<>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessuna squadra trovata");
        }

        for (Team t: list) {
            listDTO.add(entityToDTO(t));
        }

        return listDTO;
    }

    @Override
    public TeamViewDTO getTeamById(String id) {
        Optional<Team> teamOPT = teamRepository.findById(id);
        TeamViewDTO teamDTO;

        if(teamOPT.isPresent()) {
            teamDTO = entityToDTO(teamOPT.get());
        } else {
            throw new RuntimeException("Squadra non trovata");
        }

        return teamDTO;
    }

    @Override
    public List<TeamViewDTO> getTeamsByCountry(String country) {
        List<Team> list = teamRepository.findByCountry(country);
        List<TeamViewDTO> listDTO = new ArrayList<>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessuna squadra trovata");
        }

        for (Team t: list) {
            listDTO.add(entityToDTO(t));
        }

        return listDTO;
    }

    @Override
    public List<TeamViewDTO> getTeamsByStadiumId(String stadiumId) {
        ObjectId objectId = new ObjectId(stadiumId);
        List<Team> list = teamRepository.findByStadiumId(objectId);
        List<TeamViewDTO> listDTO = new ArrayList<>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessuna squadra trovata");
        }

        for (Team t: list) {
            listDTO.add(entityToDTO(t));
        }

        return listDTO;
    }

    @Override
    public TeamViewDTO createTeam(TeamCreationDTO createDTO) {
        Team team = createTeamConstructor(createDTO);

        team = teamRepository.save(team);

        return entityToDTO(team);
    }

    @Override
    public TeamViewDTO updateTeam(TeamUpdateDTO updateDTO, String id) {
        Optional<Team>teamOPT = teamRepository.findById(id);
        Team team = new Team();

        if(teamOPT.isPresent()) {
            team = updateTeamConstructor(updateDTO, teamOPT.get());
        } else {
            throw new RuntimeException("Nessuna squadra trovata");
        }

        team = teamRepository.save(team);

        return entityToDTO(team);
    }

    @Override
    public void deleteTeam(String id) {
        teamRepository.deleteById(id);
    }

    private Team createTeamConstructor(TeamCreationDTO createDTO) {
        Team team = new Team();

        team.setTeamName(createDTO.getTeamName());
        team.setCountry(createDTO.getCountry());

        ObjectId stadiumId = new ObjectId(createDTO.getStadiumId());
        Optional<Stadium> stadiumOPT = stadiumRepository.findById(createDTO.getStadiumId());
        if (stadiumOPT.isPresent()){
            team.setStadiumId(stadiumId);
        } else {
            throw new RuntimeException("Nessuno stadio trovato");
        }

        return team;
    }

    private Team updateTeamConstructor(TeamUpdateDTO updateDTO, Team team) {
        team.setTeamName(updateDTO.getTeamName());
        team.setCountry(updateDTO.getCountry());

        ObjectId stadiumId = new ObjectId(updateDTO.getStadiumId());
        Optional<Stadium> stadiumOPT = stadiumRepository.findById(updateDTO.getStadiumId());
        if (stadiumOPT.isPresent()){
            team.setStadiumId(stadiumId);
        } else {
            throw new RuntimeException("Nessuno stadio trovato");
        }

        return team;
    }

    private TeamViewDTO entityToDTO(Team team) {
        TeamViewDTO teamDTO = new TeamViewDTO();

        teamDTO.setId(team.getId());
        teamDTO.setTeamName(team.getTeamName());
        teamDTO.setCountry(team.getCountry());

        if(team.getStadiumId() != null){
            teamDTO.setStadiumId(team.getStadiumId().toString());
        }

        return teamDTO;
    }

}
