package com.example.examcell.service;

import com.example.examcell.dto.RegulationDropdownItemDTO;
import com.example.examcell.repository.RegulationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RegulationService {
    private final RegulationJpaRepository regulationJpaRepository;

    @Autowired
    public RegulationService(RegulationJpaRepository regulationJpaRepository) {
        this.regulationJpaRepository = regulationJpaRepository;
    }

    public ArrayList<RegulationDropdownItemDTO> getAllRegulationDropdownItemsDTO() {
        return new ArrayList<>(regulationJpaRepository.findAllRegulationDropdownItemsDTO());
    }
}
