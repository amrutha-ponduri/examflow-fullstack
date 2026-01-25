package com.example.examcell.controller;

import com.example.examcell.dto.DepartmentReviewerDTO;
import com.example.examcell.model.Department;
import com.example.examcell.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("")
    public ArrayList<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable("id") int id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping("")
    public DepartmentReviewerDTO addDepartment(@RequestBody DepartmentReviewerDTO departmentReviewerDTO) {
        return departmentService.addDepartment(departmentReviewerDTO);
    }

    @PutMapping("/{id}")
    public DepartmentReviewerDTO updateDepartment(@PathVariable("id") int id, DepartmentReviewerDTO departmentReviewerDTO) {
        return departmentService.updateDepartment(id, departmentReviewerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable("id") int id) {
        departmentService.deleteDepartment(id);
    }
}
