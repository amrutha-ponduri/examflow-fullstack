package com.example.examcell.service;

import com.example.examcell.config.Mapper;
import com.example.examcell.dto.DepartmentDTO;
import com.example.examcell.dto.DepartmentDropdownItemDTO;
import com.example.examcell.dto.DepartmentReviewerDTO;
import com.example.examcell.model.Department;
import com.example.examcell.model.DepartmentReviewer;
import com.example.examcell.model.DepartmentReviewerId;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService implements DepartmentRepository {

    private final DepartmentJpaRepository departmentJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final Mapper mapper;
    private final DepartmentReviewerJpaRepository departmentReviewerJpaRepository;

    @Autowired
    public DepartmentService(DepartmentJpaRepository departmentJpaRepository, UserJpaRepository userJpaRepository, Mapper mapper, DepartmentReviewerJpaRepository departmentReviewerJpaRepository) {
        this.departmentJpaRepository = departmentJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.mapper = mapper;
        this.departmentReviewerJpaRepository = departmentReviewerJpaRepository;
    }

    @Override
    public ArrayList<DepartmentDTO> getAllDepartments() {
        List<Department> departments = new ArrayList<>(departmentJpaRepository.findAll());
        List<DepartmentDTO> departmentDTOS = departments.stream().map(mapper::toDepartmentDTO).collect(Collectors.toList());
        return new ArrayList<>(departmentDTOS);
    }

    @Override
    public DepartmentDTO getDepartmentById(int id) {
        try {
            return mapper.toDepartmentDTO(departmentJpaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public DepartmentDTO addDepartment(DepartmentReviewerDTO departmentReviewerDTO) {
        try {
            int userId = departmentReviewerDTO.getUserId();
            Department department = departmentReviewerDTO.getDepartment();
            Department savedDepartment = departmentJpaRepository.save(department);
            User user = userJpaRepository.findById(userId)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "User not found with id " + departmentReviewerDTO.getUserId()
                    ));
            DepartmentReviewer departmentReviewer = new DepartmentReviewer();
            DepartmentReviewerId departmentReviewerId = new DepartmentReviewerId();
            departmentReviewerId.setDepartmentId(savedDepartment.getId());
            departmentReviewerId.setUserId(userId);
            departmentReviewer.setDepartmentReviewerId(departmentReviewerId);
            departmentReviewer.setUser(user);
            departmentReviewer.setDepartment(savedDepartment);
            savedDepartment.setDepartmentReviewer(departmentReviewer);
            return mapper.toDepartmentDTO(savedDepartment);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public DepartmentDTO updateDepartment(int id, DepartmentReviewerDTO departmentReviewerDTO) {
        Department existingDepartment = departmentJpaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Department newDepartment = departmentReviewerDTO.getDepartment();
        if (newDepartment != null) {
            if (newDepartment.getDepartmentName() != null) {
                existingDepartment.setDepartmentName(newDepartment.getDepartmentName());
            }
            if (newDepartment.getAbbreviation() != null) {
                existingDepartment.setAbbreviation(newDepartment.getAbbreviation());
            }
        }

        if (departmentReviewerDTO.getUserId() != null) {
            DepartmentReviewer existingDepartmentReviewer = existingDepartment.getDepartmentReviewer();
            existingDepartmentReviewer.setDepartment(null);

            DepartmentReviewer newDepartmentReviewer = new DepartmentReviewer();
            DepartmentReviewerId departmentReviewerId = new DepartmentReviewerId();
            User user = userJpaRepository.findById(departmentReviewerDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            departmentReviewerId.setDepartmentId(existingDepartment.getId());
            departmentReviewerId.setUserId(departmentReviewerDTO.getUserId());
            newDepartmentReviewer.setDepartmentReviewerId(departmentReviewerId);
            newDepartmentReviewer.setUser(user);
            newDepartmentReviewer.setDepartment(existingDepartment);

            existingDepartment.setDepartmentReviewer(newDepartmentReviewer);
        }
        departmentJpaRepository.save(existingDepartment);
        return mapper.toDepartmentDTO(existingDepartment);
    }

    @Override
    @Transactional
    public void deleteDepartment(int id) {
        Department department = departmentJpaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        departmentJpaRepository.delete(department);
    }

    @Override
    public ArrayList<DepartmentDropdownItemDTO> getAllDepartmentDropDownItems() {
        return new ArrayList<>(departmentJpaRepository.findAllDepartmentDropdownItemsDTO());
    }
}
