package com.example.examcell.repository;

import com.example.examcell.model.Department;
import com.example.examcell.model.DepartmentReviewer;
import com.example.examcell.model.DepartmentReviewerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentReviewerJpaRepository extends JpaRepository<DepartmentReviewer, DepartmentReviewerId> {
    DepartmentReviewer findByDepartment(Department department);

}
