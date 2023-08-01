package com.db2.uefastatistics.service.stadium;

import com.db2.uefastatistics.dto.stadium.StadiumCreationDTO;
import com.db2.uefastatistics.dto.stadium.StadiumUpdateDTO;
import com.db2.uefastatistics.dto.stadium.StadiumViewDTO;
import com.db2.uefastatistics.model.Stadium;
import com.db2.uefastatistics.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StadiumServiceImpl implements StadiumService{

    @Autowired
    public StadiumRepository stadiumRepository;

    @Override
    public List<StadiumViewDTO> getAllStadiums() {
        List<Stadium> list = stadiumRepository.findAll();
        List<StadiumViewDTO> listDTO = new ArrayList<StadiumViewDTO>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessuno stadio trovato");
        }

        for (Stadium s: list) {
            listDTO.add(entityToDTO(s));
        }
        
        return listDTO;
    }

    @Override
    public StadiumViewDTO getStadiumById(String id) {
        Optional<Stadium> stadiumOPT = stadiumRepository.findById(id);
        StadiumViewDTO stadiumDTO;

        if(stadiumOPT.isPresent()) {
            stadiumDTO = entityToDTO(stadiumOPT.get());
        } else {
            throw new RuntimeException("Stadio non trovato");
        }

        return stadiumDTO;
    }

    @Override
    public List<StadiumViewDTO> getStadiumsByCountry(String country) {
        List<Stadium> list = stadiumRepository.findByCountry(country);
        List<StadiumViewDTO> listDTO = new ArrayList<StadiumViewDTO>();

        if(list.isEmpty()) {
            throw new RuntimeException("Nessuno stadio trovato");
        }

        for (Stadium s: list) {
            listDTO.add(entityToDTO(s));
        }

        return listDTO;
    }

    @Override
    public StadiumViewDTO createStadium(StadiumCreationDTO createDTO) {
        Stadium stadium = createStadiumConstructor(createDTO);

        stadium = stadiumRepository.save(stadium);

        return entityToDTO(stadium);
    }

    @Override
    public StadiumViewDTO updateStadium(StadiumUpdateDTO updateDTO, String id) {
        Optional<Stadium> stadiumOPT = stadiumRepository.findById(id);
        Stadium stadium = new Stadium();

        if(stadiumOPT.isPresent()) {
            stadium = updateStadiumConstructor(updateDTO, stadiumOPT.get());
        } else {
            throw new RuntimeException("Nessuno stadio trovato");
        }

        stadium = stadiumRepository.save(stadium);

        return entityToDTO(stadium);
    }

    @Override
    public void deleteStadium(String id) {
        stadiumRepository.deleteById(id);
    }

    private Stadium createStadiumConstructor(StadiumCreationDTO createDTO) {
        Stadium stadium = new Stadium();

        stadium.setStadiumName(createDTO.getStadiumName());
        stadium.setCity(createDTO.getCity());
        stadium.setCountry(createDTO.getCountry());
        stadium.setCapacity(createDTO.getCapacity());
        stadium.setLatitude(createDTO.getLatitude());
        stadium.setLongitude(createDTO.getLongitude());

        return stadium;
    }

    private Stadium updateStadiumConstructor(StadiumUpdateDTO updateDTO, Stadium stadium) {

        stadium.setStadiumName(updateDTO.getStadiumName());
        stadium.setCity(updateDTO.getCity());
        stadium.setCountry(updateDTO.getCountry());
        stadium.setCapacity(updateDTO.getCapacity());
        stadium.setLatitude(updateDTO.getLatitude());
        stadium.setLongitude(updateDTO.getLongitude());

        return stadium;
    }

    private StadiumViewDTO entityToDTO(Stadium stadium) {
        StadiumViewDTO stadiumDTO = new StadiumViewDTO();

        stadiumDTO.setId(stadium.getId());
        stadiumDTO.setStadiumName(stadium.getStadiumName());
        stadiumDTO.setCity(stadium.getCity());
        stadiumDTO.setCountry(stadium.getCountry());
        stadiumDTO.setCapacity(stadium.getCapacity());
        stadiumDTO.setLatitude(stadium.getLatitude());
        stadiumDTO.setLongitude(stadium.getLongitude());

        return stadiumDTO;
    }

}
