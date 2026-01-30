package com.example.examcell.repository.contractrepos;

import com.example.examcell.dto.dropdowndtos.ProgramDropdownItemDTO;
import com.example.examcell.model.Program;

import java.util.ArrayList;

public interface ProgramRepository {
    public ArrayList<Program> getAllPrograms();

    public Program getProgramById(int id);

    public Program addProgram(Program program);

    public Program updateProgram(int id, Program program);

    public void deleteProgramById(int id);

    ArrayList<ProgramDropdownItemDTO> getAllProgramDropdownItemsDTO();
}
