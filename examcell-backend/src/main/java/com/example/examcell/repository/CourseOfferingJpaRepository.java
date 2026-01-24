package com.example.examcell.repository;

import com.example.examcell.model.CourseOffering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseOfferingJpaRepository extends JpaRepository<CourseOffering, Integer> {
    public List<CourseOffering> findByInstructorsUsername(String username);
}
