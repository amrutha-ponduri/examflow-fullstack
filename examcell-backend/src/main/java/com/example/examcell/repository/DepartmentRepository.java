package com.example.examcell.repository;

import com.example.examcell.dto.DepartmentDropdownItemDTO;
import com.example.examcell.dto.DepartmentReviewerDTO;
import com.example.examcell.model.Department;

import java.util.ArrayList;

public interface DepartmentRepository {
    public ArrayList<Department> getAllDepartments();

    public Department getDepartmentById(int id);

    public DepartmentReviewerDTO addDepartment(DepartmentReviewerDTO departmentReviewerDTO);

    public DepartmentReviewerDTO updateDepartment(int id, DepartmentReviewerDTO departmentReviewerDTO);

    public void deleteDepartment(int id);

    ArrayList<DepartmentDropdownItemDTO> getAllDepartmentDropDownItems();
}
