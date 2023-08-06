package com.db2.uefastatistics.controller;

import com.db2.uefastatistics.dto.manager.ManagerCreationDTO;
import com.db2.uefastatistics.dto.manager.ManagerUpdateDTO;
import com.db2.uefastatistics.dto.manager.ManagerViewDTO;
import com.db2.uefastatistics.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("public/manager")
public class ManagerController {

    @Autowired
    public ManagerService managerService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<ManagerViewDTO>> getAllManagers() {
        List<ManagerViewDTO> listDTO = managerService.getAllManagers();
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/allByNationality/{nationality}")
    public ResponseEntity<List<ManagerViewDTO>> getManagersByNationality(@PathVariable(required = true, name = "nationality") String nationality) {
        List<ManagerViewDTO> listDTO = managerService.getManagersByNationality(nationality);
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/allByTeamId/{teamId}")
    public ResponseEntity<List<ManagerViewDTO>> getManagersByTeamId(@PathVariable(required = true, name = "teamId") String teamId) {
        List<ManagerViewDTO> listDTO = managerService.getManagersByTeamId(teamId);
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ManagerViewDTO> getManagerById(@PathVariable(required = true, name = "id") String id) {
        ManagerViewDTO managerDTO = managerService.getManagerById(id);
        return new ResponseEntity<>(managerDTO, HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ManagerViewDTO> createManager(@RequestBody ManagerCreationDTO managerCreationDTO) {
        ManagerViewDTO managerDTO = managerService.createManager(managerCreationDTO);
        return new ResponseEntity<>(managerDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<ManagerViewDTO> updateManager(
            @RequestBody ManagerUpdateDTO managerUpdateDTO,
            @PathVariable(required = true, name = "id") String id
        ) {
        ManagerViewDTO managerDTO = managerService.updateManager(managerUpdateDTO, id);
        return new ResponseEntity<>(managerDTO, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable(required = true, name = "id") String id) {
        managerService.deleteManager(id);
        return ResponseEntity.noContent().build();
    }

}
