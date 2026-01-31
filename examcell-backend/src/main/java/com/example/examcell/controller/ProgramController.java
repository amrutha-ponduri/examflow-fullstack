package com.example.examcell.controller;

import com.example.examcell.dto.dropdowndtos.ProgramDropdownItemDTO;
import com.example.examcell.model.Program;
import com.example.examcell.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/programs")
public class ProgramController {

    private final ProgramService programService;

    @Autowired
    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping("")
    public ArrayList<Program> getAllPrograms() {
        return programService.getAllPrograms();
    }

    @GetMapping("/{id}")
    public Program getProgramById(@PathVariable("id") int id) {
        return programService.getProgramById(id);
    }

    @PostMapping("")
    public Program addProgram(@RequestBody Program program) {
        return programService.addProgram(program);
    }

    @PutMapping("/{id}")
    public Program updateProgram(@PathVariable("id") int id, @RequestBody Program program) {
        return programService.updateProgram(id, program);
    }

    @DeleteMapping("/{id}")
    public void deleteProgramById(@PathVariable("id") int id) {
        programService.deleteProgramById(id);
    }

    @GetMapping("/dropdown")
    public ArrayList<ProgramDropdownItemDTO> getAllProgramDropdownItemsDTO() {
        return programService.getAllProgramDropdownItemsDTO();
    }
}
