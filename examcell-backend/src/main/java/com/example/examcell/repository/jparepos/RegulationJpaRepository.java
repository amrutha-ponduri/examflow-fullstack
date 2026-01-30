package com.example.examcell.repository.jparepos;

import com.example.examcell.dto.dropdowndtos.RegulationDropdownItemDTO;
import com.example.examcell.model.Regulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegulationJpaRepository extends JpaRepository<Regulation, Integer> {

    @Query("""
            SELECT new com.example.examcell.dto.dropdowndtos.RegulationDropdownItemDTO(r.id, r.regulationName)
            FROM Regulation r""")
    List<RegulationDropdownItemDTO> findAllRegulationDropdownItemsDTO();
}
