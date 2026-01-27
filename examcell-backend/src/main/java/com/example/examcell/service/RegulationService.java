package com.example.examcell.service;

import com.example.examcell.dto.RegulationDropdownItemDTO;
import com.example.examcell.model.Regulation;
import com.example.examcell.model.SectionRules;
import com.example.examcell.repository.RegulationJpaRepository;
import com.example.examcell.repository.RegulationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegulationService implements RegulationRepository {
    private final RegulationJpaRepository regulationJpaRepository;


    @Autowired
    public RegulationService(RegulationJpaRepository regulationJpaRepository) {
        this.regulationJpaRepository = regulationJpaRepository;
    }

    @Override
    public ArrayList<Regulation> getAllRegulations() {
        return new ArrayList<>(regulationJpaRepository.findAll());
    }

    @Override
    public Regulation getRegulationById(int id) {
        return regulationJpaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public ArrayList<RegulationDropdownItemDTO> getAllRegulationDropdownItemsDTO() {
        return new ArrayList<>(regulationJpaRepository.findAllRegulationDropdownItemsDTO());
    }

    @Override
    @Transactional
    public Regulation addRegulation(Regulation regulation) {
        List<SectionRules> sectionsRules = regulation.getSectionsRules();
        Regulation savedRegulation = regulationJpaRepository.save(regulation);
        if (sectionsRules != null) {
            sectionsRules.forEach((sectionRules -> sectionRules.setRegulation(savedRegulation)));
            savedRegulation.setSectionsRules(sectionsRules);
        }
        return savedRegulation;
    }

    @Override
    @Transactional
    public Regulation updateRegulation(int id, Regulation regulation) {
        Regulation savedRegulation = getRegulationById(id);
        if (regulation.getRegulationName() != null) {
            savedRegulation.setRegulationName(regulation.getRegulationName());
        }
        if (regulation.getSectionsRules() != null) {
            savedRegulation.getSectionsRules().clear();
            List<SectionRules> newSectionRules = regulation.getSectionsRules();
            newSectionRules.forEach((sectionRules) -> sectionRules.setRegulation(savedRegulation));
            savedRegulation.getSectionsRules().addAll(newSectionRules);
        }
        return regulationJpaRepository.save(savedRegulation);
    }

    @Override
    @Transactional
    public void deleteRegulation(int id) {
        Regulation savedRegulation = getRegulationById(id);
        regulationJpaRepository.delete(savedRegulation);
    }

}
