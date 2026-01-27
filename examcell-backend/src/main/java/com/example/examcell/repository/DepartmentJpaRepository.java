package com.example.examcell.repository;

import com.example.examcell.dto.DepartmentDropdownItemDTO;
import com.example.examcell.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentJpaRepository extends JpaRepository<Department, Integer> {
    @Query("""
            SELECT new com.example.examcell.dto.DepartmentDropdownItemDTO(d.id, d.abbreviation)
            FROM Department d""")
    public List<DepartmentDropdownItemDTO> findAllDepartmentDropdownItemsDTO();
}
