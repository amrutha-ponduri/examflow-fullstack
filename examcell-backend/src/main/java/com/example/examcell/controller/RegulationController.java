package com.example.examcell.controller;

import com.example.examcell.dto.dropdowndtos.RegulationDropdownItemDTO;
import com.example.examcell.model.Regulation;
import com.example.examcell.service.RegulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/regulations")
public class RegulationController {
    private final RegulationService regulationService;

    @Autowired
    public RegulationController(RegulationService regulationService) {
        this.regulationService = regulationService;
    }

    @GetMapping("")
    public ArrayList<Regulation> getAllRegulations() {
        return regulationService.getAllRegulations();
    }

    @GetMapping("/{id}")
    public Regulation getRegulationById(@PathVariable("id") int id) {
        return regulationService.getRegulationById(id);
    }

    @PostMapping("")
    public Regulation addRegulation(@RequestBody Regulation regulation) {
        return regulationService.addRegulation(regulation);
    }

    @PutMapping("/{id}")
    public Regulation updateRegulation(@PathVariable("id") int id, @RequestBody Regulation regulation) {
        return regulationService.updateRegulation(id, regulation);
    }

    @DeleteMapping("/{id}")
    public void deleteRegulation(@PathVariable("id") int id) {
        regulationService.deleteRegulation(id);
    }

    @GetMapping("/dropdown")
    public ArrayList<RegulationDropdownItemDTO> getAllRegulationDropdownItemsDTO() {
        return regulationService.getAllRegulationDropdownItemsDTO();
    }
}
