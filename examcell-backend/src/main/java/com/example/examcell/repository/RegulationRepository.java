package com.example.examcell.repository;

import com.example.examcell.dto.RegulationDropdownItemDTO;
import com.example.examcell.model.Regulation;

import java.util.ArrayList;

public interface RegulationRepository {
    ArrayList<Regulation> getAllRegulations();

    Regulation getRegulationById(int id);

    Regulation addRegulation(Regulation regulation);

    Regulation updateRegulation(int id, Regulation regulation);

    void deleteRegulation(int id);

    ArrayList<RegulationDropdownItemDTO> getAllRegulationDropdownItemsDTO();
}
