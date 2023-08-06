package com.db2.uefastatistics.service.player;

import com.db2.uefastatistics.dto.player.PlayerCreationDTO;
import com.db2.uefastatistics.dto.player.PlayerUpdateDTO;
import com.db2.uefastatistics.dto.player.PlayerViewDTO;
import com.db2.uefastatistics.model.Player;
import com.db2.uefastatistics.model.Team;
import com.db2.uefastatistics.repository.PlayerRepository;
import com.db2.uefastatistics.repository.TeamRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService{
    
    @Autowired
    public PlayerRepository playerRepository;
    
    @Autowired
    public TeamRepository teamRepository;

    @Override
    public List<PlayerViewDTO> getAllPlayers() {
        List<Player> list = playerRepository.findAll();
        List<PlayerViewDTO> listDTO = new ArrayList<>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessun giocatore trovato");
        }

        for (Player p: list) {
            listDTO.add(entityToDTO(p));
        }

        return listDTO;
    }

    @Override
    public PlayerViewDTO getPlayerById(String id) {
        Optional<Player> playerOPT = playerRepository.findById(id);
        PlayerViewDTO playerDTO;

        if(playerOPT.isPresent()) {
            playerDTO = entityToDTO(playerOPT.get());
        } else {
            throw new RuntimeException("Giocatore non trovato");
        }

        return playerDTO;
    }

    @Override
    public List<PlayerViewDTO> getPlayersByNationality(String nationality) {
        List<Player> list = playerRepository.findByNationality(nationality);
        List<PlayerViewDTO> listDTO = new ArrayList<>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessun giocatore trovato");
        }

        for (Player p: list) {
            listDTO.add(entityToDTO(p));
        }

        return listDTO;
    }

    @Override
    public List<PlayerViewDTO> getPlayersByTeamId(String teamId) {
        ObjectId objectId = new ObjectId(teamId);
        List<Player> list = playerRepository.findByTeamId(objectId);
        List<PlayerViewDTO> listDTO = new ArrayList<>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessun giocatore trovato");
        }

        for (Player p: list) {
            listDTO.add(entityToDTO(p));
        }

        return listDTO;
    }

    @Override
    public PlayerViewDTO createPlayer(PlayerCreationDTO createDTO) {
        Player player = createPlayerConstructor(createDTO);

        player = playerRepository.save(player);

        return entityToDTO(player);
    }

    @Override
    public PlayerViewDTO updatePlayer(PlayerUpdateDTO updateDTO, String id) {
        Optional<Player> playerOPT = playerRepository.findById(id);
        Player player = new Player();

        if(playerOPT.isPresent()) {
            player = updatePlayerConstructor(updateDTO, playerOPT.get());
        } else {
            throw new RuntimeException("Nessun giocatore trovato");
        }

        player = playerRepository.save(player);

        return entityToDTO(player);
    }

    @Override
    public void deletePlayer(String id) {
        playerRepository.deleteById(id);
    }

    private Player createPlayerConstructor(PlayerCreationDTO createDTO) {
        Player player = new Player();

        player.setFirstName(createDTO.getFirstName());
        player.setLastName(createDTO.getLastName());
        player.setNationality(createDTO.getNationality());
        player.setBirthday(createDTO.getBirthday());
        player.setJerseyNumber(createDTO.getJerseyNumber());
        player.setPosition(createDTO.getPosition());
        player.setHeight(createDTO.getHeight());
        player.setWeight(createDTO.getWeight());
        player.setFoot(createDTO.getFoot());

        ObjectId teamId = new ObjectId(createDTO.getTeamId());
        Optional<Team> teamOPT = teamRepository.findById(createDTO.getTeamId());
        if (teamOPT.isPresent()){
            player.setTeamId(teamId);
        } else {
            throw new RuntimeException("Nessuna squadra trovata");
        }

        return player;
    }

    private Player updatePlayerConstructor(PlayerUpdateDTO updateDTO, Player player) {
        player.setFirstName(updateDTO.getFirstName());
        player.setLastName(updateDTO.getLastName());
        player.setNationality(updateDTO.getNationality());
        player.setBirthday(updateDTO.getBirthday());
        player.setJerseyNumber(updateDTO.getJerseyNumber());
        player.setPosition(updateDTO.getPosition());
        player.setHeight(updateDTO.getHeight());
        player.setWeight(updateDTO.getWeight());
        player.setFoot(updateDTO.getFoot());

        ObjectId teamId = new ObjectId(updateDTO.getTeamId());
        Optional<Team> teamOPT = teamRepository.findById(updateDTO.getTeamId());
        if (teamOPT.isPresent()){
            player.setTeamId(teamId);
        } else {
            throw new RuntimeException("Nessuna squadra trovata");
        }

        return player;
    }

    private PlayerViewDTO entityToDTO(Player player) {
        PlayerViewDTO playerDTO = new PlayerViewDTO();

        playerDTO.setId(player.getId());
        playerDTO.setFirstName(player.getFirstName());
        playerDTO.setLastName(player.getLastName());
        playerDTO.setNationality(player.getNationality());
        playerDTO.setBirthday(player.getBirthday());
        playerDTO.setJerseyNumber(player.getJerseyNumber());
        playerDTO.setPosition(player.getPosition());
        playerDTO.setHeight(player.getHeight());
        playerDTO.setWeight(player.getWeight());
        playerDTO.setFoot(player.getFoot());

        if(player.getTeamId() != null){
            playerDTO.setTeamId(player.getTeamId().toString());
        }

        return playerDTO;
    }
    
}
