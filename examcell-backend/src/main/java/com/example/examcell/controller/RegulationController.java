package com.example.examcell.controller;

import com.example.examcell.dto.RegulationDropdownItemDTO;
import com.example.examcell.service.RegulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/regulations")
public class RegulationController {
    private final RegulationService regulationService;

    @Autowired
    public RegulationController(RegulationService regulationService) {
        this.regulationService = regulationService;
    }

    @GetMapping("/dropdown")
    public ArrayList<RegulationDropdownItemDTO> getAllRegulationDropdownItemsDTO() {
        return regulationService.getAllRegulationDropdownItemsDTO();
    }
}
