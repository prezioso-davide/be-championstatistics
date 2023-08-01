package com.db2.uefastatistics.service.stadium;

import com.db2.uefastatistics.dto.stadium.StadiumCreationDTO;
import com.db2.uefastatistics.dto.stadium.StadiumUpdateDTO;
import com.db2.uefastatistics.dto.stadium.StadiumViewDTO;
import com.db2.uefastatistics.model.Stadium;

import java.util.List;

public interface StadiumService {

    List<StadiumViewDTO> getAllStadiums();
    StadiumViewDTO getStadiumById(String id);
    List<StadiumViewDTO> getStadiumsByCountry(String country);
    StadiumViewDTO createStadium(StadiumCreationDTO createDTO);
    StadiumViewDTO updateStadium(StadiumUpdateDTO updateDTO, String id);
    void deleteStadium(String id);

}
