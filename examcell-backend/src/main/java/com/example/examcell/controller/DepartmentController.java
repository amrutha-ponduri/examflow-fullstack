package com.example.examcell.controller;

import com.example.examcell.dto.departmentdtos.DepartmentDTO;
import com.example.examcell.dto.departmentdtos.DepartmentReviewerDTO;
import com.example.examcell.dto.dropdowndtos.DepartmentDropdownItemDTO;
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
    public ArrayList<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public DepartmentDTO getDepartmentById(@PathVariable("id") int id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping("")
    public DepartmentDTO addDepartment(@RequestBody DepartmentReviewerDTO departmentReviewerDTO) {
        return departmentService.addDepartment(departmentReviewerDTO);
    }

    @PutMapping("/{id}")
    public DepartmentDTO updateDepartment(@PathVariable("id") int id, @RequestBody DepartmentReviewerDTO departmentReviewerDTO) {
        return departmentService.updateDepartment(id, departmentReviewerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable("id") int id) {
        departmentService.deleteDepartment(id);
    }

    @GetMapping("/dropdown")
    public ArrayList<DepartmentDropdownItemDTO> getAllDepartmentDropDownItems() {
        return departmentService.getAllDepartmentDropDownItems();
    }
}
