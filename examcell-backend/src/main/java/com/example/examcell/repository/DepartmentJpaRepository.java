package com.example.examcell.repository;

import com.example.examcell.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentJpaRepository extends JpaRepository<Department, Integer> {

}
