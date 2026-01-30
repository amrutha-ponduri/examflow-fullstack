package com.example.examcell.repository.jparepos;

import com.example.examcell.dto.dropdowndtos.ProgramDropdownItemDTO;
import com.example.examcell.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramJpaRepository extends JpaRepository<Program, Integer> {
    @Query("""
            SELECT new com.example.examcell.dto.dropdowndtos.ProgramDropdownItemDTO(p.id, p.programName)
            FROM Program p""")
    List<ProgramDropdownItemDTO> findAllProgramDropdownItemsDTO();
}
