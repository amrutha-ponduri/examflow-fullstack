package com.example.examcell.repository.jparepos;

import com.example.examcell.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseJpaRepository extends JpaRepository<Course, Integer> {

}
