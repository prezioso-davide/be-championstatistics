package com.db2.uefastatistics.service.manager;

import com.db2.uefastatistics.dto.manager.ManagerCreationDTO;
import com.db2.uefastatistics.dto.manager.ManagerUpdateDTO;
import com.db2.uefastatistics.dto.manager.ManagerViewDTO;
import com.db2.uefastatistics.model.Manager;
import com.db2.uefastatistics.model.Team;
import com.db2.uefastatistics.repository.ManagerRepository;
import com.db2.uefastatistics.repository.TeamRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<ManagerViewDTO> getAllManagers() {
        List<Manager> list = managerRepository.findAll();
        List<ManagerViewDTO> listDTO = new ArrayList<>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessun allenatore trovato");
        }

        for (Manager p: list) {
            listDTO.add(entityToDTO(p));
        }

        return listDTO;
    }

    @Override
    public ManagerViewDTO getManagerById(String id) {
        Optional<Manager> managerOPT = managerRepository.findById(id);
        ManagerViewDTO managerDTO;

        if(managerOPT.isPresent()) {
            managerDTO = entityToDTO(managerOPT.get());
        } else {
            throw new RuntimeException("Allenatore non trovato");
        }

        return managerDTO;
    }

    @Override
    public List<ManagerViewDTO> getManagersByNationality(String nationality) {
        List<Manager> list = managerRepository.findByNationality(nationality);
        List<ManagerViewDTO> listDTO = new ArrayList<>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessun allenatore trovato");
        }

        for (Manager p: list) {
            listDTO.add(entityToDTO(p));
        }

        return listDTO;
    }

    @Override
    public List<ManagerViewDTO> getManagersByTeamId(String teamId) {
        ObjectId objectId = new ObjectId(teamId);
        List<Manager> list = managerRepository.findByTeamId(objectId);
        List<ManagerViewDTO> listDTO = new ArrayList<>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessun allenatore trovato");
        }

        for (Manager p: list) {
            listDTO.add(entityToDTO(p));
        }

        return listDTO;
    }

    @Override
    public ManagerViewDTO createManager(ManagerCreationDTO createDTO) {
        Manager manager = createManagerConstructor(createDTO);

        manager = managerRepository.save(manager);

        return entityToDTO(manager);
    }

    @Override
    public ManagerViewDTO updateManager(ManagerUpdateDTO updateDTO, String id) {
        Optional<Manager> managerOPT = managerRepository.findById(id);
        Manager manager = new Manager();

        if(managerOPT.isPresent()) {
            manager = updateManagerConstructor(updateDTO, managerOPT.get());
        } else {
            throw new RuntimeException("Nessun allenatore trovato");
        }

        manager = managerRepository.save(manager);

        return entityToDTO(manager);
    }

    @Override
    public void deleteManager(String id) {
        managerRepository.deleteById(id);
    }

    private Manager createManagerConstructor(ManagerCreationDTO createDTO) {
        Manager manager = new Manager();

        manager.setFirstName(createDTO.getFirstName());
        manager.setLastName(createDTO.getLastName());
        manager.setNationality(createDTO.getNationality());
        manager.setBirthday(createDTO.getBirthday());

        ObjectId teamId = new ObjectId(createDTO.getTeamId());
        Optional<Team> teamOPT = teamRepository.findById(createDTO.getTeamId());
        if (teamOPT.isPresent()){
            manager.setTeamId(teamId);
        } else {
            throw new RuntimeException("Nessuna squadra trovata");
        }

        return manager;
    }

    private Manager updateManagerConstructor(ManagerUpdateDTO updateDTO, Manager manager) {
        manager.setFirstName(updateDTO.getFirstName());
        manager.setLastName(updateDTO.getLastName());
        manager.setNationality(updateDTO.getNationality());
        manager.setBirthday(updateDTO.getBirthday());

        ObjectId teamId = new ObjectId(updateDTO.getTeamId());
        Optional<Team> teamOPT = teamRepository.findById(updateDTO.getTeamId());
        if (teamOPT.isPresent()){
            manager.setTeamId(teamId);
        } else {
            throw new RuntimeException("Nessuna squadra trovata");
        }

        return manager;
    }

    private ManagerViewDTO entityToDTO(Manager manager) {
        ManagerViewDTO managerDTO = new ManagerViewDTO();

        managerDTO.setId(manager.getId());
        managerDTO.setFirstName(manager.getFirstName());
        managerDTO.setLastName(manager.getLastName());
        managerDTO.setNationality(manager.getNationality());
        managerDTO.setBirthday(manager.getBirthday());

        if(manager.getTeamId() != null){
            managerDTO.setTeamId(manager.getTeamId().toString());
        }

        return managerDTO;
    }

}
