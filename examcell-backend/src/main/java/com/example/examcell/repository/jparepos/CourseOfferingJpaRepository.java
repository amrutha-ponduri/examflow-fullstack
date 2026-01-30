package com.example.examcell.repository.jparepos;

import com.example.examcell.dto.courseofferingdtos.CourseOfferingDTO;
import com.example.examcell.model.CourseOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CourseOfferingJpaRepository extends JpaRepository<CourseOffering, Integer> {

    @Query("""
            SELECT
            new com.example.examcell.dto.courseofferingdtos.CourseOfferingDTO(co.id, co.semester, co.yearOfStudy, co.department.abbreviation, co.regulation.regulationName, co.course.courseTitle)
            FROM CourseOffering co""")
    public ArrayList<CourseOfferingDTO> findAllCourseOfferings();
}
