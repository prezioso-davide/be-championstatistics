package com.db2.uefastatistics.service.player;

import com.db2.uefastatistics.dto.player.PlayerCreationDTO;
import com.db2.uefastatistics.dto.player.PlayerUpdateDTO;
import com.db2.uefastatistics.dto.player.PlayerViewDTO;

import java.util.List;

public interface PlayerService {

    List<PlayerViewDTO> getAllPlayers();
    PlayerViewDTO getPlayerById(String id);
    List<PlayerViewDTO> getPlayersByNationality(String nationality);
    List<PlayerViewDTO> getPlayersByTeamId(String teamId);
    PlayerViewDTO createPlayer(PlayerCreationDTO creationDTO);
    PlayerViewDTO updatePlayer(PlayerUpdateDTO updateDTO, String id);
    void deletePlayer(String id);

}
