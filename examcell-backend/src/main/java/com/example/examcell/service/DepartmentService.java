package com.example.examcell.service;

import com.example.examcell.dto.DepartmentDropdownItemDTO;
import com.example.examcell.dto.DepartmentReviewerDTO;
import com.example.examcell.model.Department;
import com.example.examcell.model.DepartmentReviewer;
import com.example.examcell.model.User;
import com.example.examcell.repository.DepartmentJpaRepository;
import com.example.examcell.repository.DepartmentRepository;
import com.example.examcell.repository.DepartmentReviewerJpaRepository;
import com.example.examcell.repository.UserJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
public class DepartmentService implements DepartmentRepository {

    private final DepartmentJpaRepository departmentJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final DepartmentReviewerJpaRepository departmentReviewerJpaRepository;

    @Autowired
    public DepartmentService(DepartmentJpaRepository departmentJpaRepository, UserJpaRepository userJpaRepository, DepartmentReviewerJpaRepository departmentReviewerJpaRepository) {
        this.departmentJpaRepository = departmentJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.departmentReviewerJpaRepository = departmentReviewerJpaRepository;
    }

    @Override
    public ArrayList<Department> getAllDepartments() {
        return new ArrayList<>(departmentJpaRepository.findAll());
    }

    @Override
    public Department getDepartmentById(int id) {
        try {
            return departmentJpaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public DepartmentReviewerDTO addDepartment(DepartmentReviewerDTO departmentReviewerDTO) {
        try {
            int userId = departmentReviewerDTO.getUserId();
            Department department = departmentReviewerDTO.getDepartment();
            Department savedDepartment = departmentJpaRepository.save(department);
            User user = userJpaRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            DepartmentReviewer departmentReviewer = new DepartmentReviewer();
            departmentReviewer.setDepartment(department);
            departmentReviewer.setUser(user);
            departmentReviewerJpaRepository.save(departmentReviewer);
            return new DepartmentReviewerDTO(savedDepartment, userId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public DepartmentReviewerDTO updateDepartment(int id, DepartmentReviewerDTO departmentReviewerDTO) {
        Department existingDepartment = getDepartmentById(id);
        DepartmentReviewer departmentReviewer = departmentReviewerJpaRepository.findByDepartment(existingDepartment);
        Department newDepartment = departmentReviewerDTO.getDepartment();

        if (newDepartment.getDepartmentName() != null) {
            existingDepartment.setDepartmentName(newDepartment.getDepartmentName());
        }
        if (newDepartment.getAbbreviation() != null) {
            existingDepartment.setAbbreviation(newDepartment.getAbbreviation());
        }
        if (departmentReviewerDTO.getUserId() != null) {
            User user = userJpaRepository.findById(departmentReviewerDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            departmentReviewer.setUser(user);
        }

        departmentReviewer.setDepartment(existingDepartment);
        departmentJpaRepository.save(existingDepartment);
        departmentReviewerJpaRepository.save(departmentReviewer);
        return new DepartmentReviewerDTO(existingDepartment, departmentReviewer.getUser().getId());
    }

    @Override
    @Transactional
    public void deleteDepartment(int id) {
        Department department = getDepartmentById(id);
        DepartmentReviewer departmentReviewer = departmentReviewerJpaRepository.findByDepartment(department);
        if (departmentReviewer != null) {
            departmentReviewerJpaRepository.delete(departmentReviewer);
        }
        departmentJpaRepository.delete(department);
    }

    @Override
    public ArrayList<DepartmentDropdownItemDTO> getAllDepartmentDropDownItems() {
        return new ArrayList<>(departmentJpaRepository.findAllDepartmentDropdownItemsDTO());
    }
}
