package com.example.examcell.repository;

import com.example.examcell.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseJpaRepository extends JpaRepository<Course, String> {

}
