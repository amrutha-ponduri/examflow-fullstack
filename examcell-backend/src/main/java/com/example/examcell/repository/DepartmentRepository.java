package com.example.examcell.repository;

import com.example.examcell.dto.DepartmentDTO;
import com.example.examcell.dto.DepartmentDropdownItemDTO;
import com.example.examcell.dto.DepartmentReviewerDTO;

import java.util.ArrayList;

public interface DepartmentRepository {
    public ArrayList<DepartmentDTO> getAllDepartments();

    public DepartmentDTO getDepartmentById(int id);

    public DepartmentDTO addDepartment(DepartmentReviewerDTO departmentReviewerDTO);

    public DepartmentDTO updateDepartment(int id, DepartmentReviewerDTO departmentReviewerDTO);

    public void deleteDepartment(int id);

    ArrayList<DepartmentDropdownItemDTO> getAllDepartmentDropDownItems();
}
