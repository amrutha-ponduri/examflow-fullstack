package com.example.examcell.repository.contractrepos;

import com.example.examcell.dto.dropdowndtos.RegulationDropdownItemDTO;
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
