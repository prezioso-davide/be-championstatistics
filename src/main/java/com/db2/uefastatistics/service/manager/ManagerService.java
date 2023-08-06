package com.db2.uefastatistics.service.manager;

import com.db2.uefastatistics.dto.manager.ManagerCreationDTO;
import com.db2.uefastatistics.dto.manager.ManagerUpdateDTO;
import com.db2.uefastatistics.dto.manager.ManagerViewDTO;

import java.util.List;

public interface ManagerService {

    List<ManagerViewDTO> getAllManagers();
    ManagerViewDTO getManagerById(String id);
    List<ManagerViewDTO> getManagersByNationality(String nationality);
    List<ManagerViewDTO> getManagersByTeamId(String teamId);
    ManagerViewDTO createManager(ManagerCreationDTO creationDTO);
    ManagerViewDTO updateManager(ManagerUpdateDTO updateDTO, String id);
    void deleteManager(String id);

}
