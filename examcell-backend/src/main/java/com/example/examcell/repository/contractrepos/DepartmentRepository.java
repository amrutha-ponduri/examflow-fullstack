package com.example.examcell.repository.contractrepos;

import com.example.examcell.dto.departmentdtos.DepartmentDTO;
import com.example.examcell.dto.departmentdtos.DepartmentReviewerDTO;
import com.example.examcell.dto.dropdowndtos.DepartmentDropdownItemDTO;

import java.util.ArrayList;

public interface DepartmentRepository {
    public ArrayList<DepartmentDTO> getAllDepartments();

    public DepartmentDTO getDepartmentById(int id);

    public DepartmentDTO addDepartment(DepartmentReviewerDTO departmentReviewerDTO);

    public DepartmentDTO updateDepartment(int id, DepartmentReviewerDTO departmentReviewerDTO);

    public void deleteDepartment(int id);

    ArrayList<DepartmentDropdownItemDTO> getAllDepartmentDropDownItems();
}
