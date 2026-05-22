package com.example.examcell.repository.jparepos;

import com.example.examcell.dto.courseofferingdtos.CourseOfferingDTO;
import com.example.examcell.model.CourseOffering;
import com.example.examcell.model.ModuleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseOfferingJpaRepository extends JpaRepository<CourseOffering, Integer> {

    @Query("""
            SELECT
            new com.example.examcell.dto.courseofferingdtos.CourseOfferingDTO(co.id, co.semester, co.yearOfStudy, co.department.abbreviation, co.regulation.regulationName, co.course.courseTitle)
            FROM CourseOffering co""")
    List<CourseOfferingDTO> findAllCourseOfferings();

    @Query("""
                SELECT DISTINCT mi
                FROM CourseOffering co
                JOIN co.moduleInfos mi
                WHERE co.department.id = :departmentId
                  AND co.course.id = :courseId
                  AND co.program.id = :programId
            """)
    List<ModuleInfo> findAllModuleInfos(
            Integer courseId,
            Integer departmentId,
            Integer programId
    );

    @Query("""
            SELECT co FROM CourseOffering co
            WHERE co.department.id = :departmentId
                  AND co.course.id = :courseId
                  AND co.program.id = :programId""")
    CourseOffering findCourseOfferingByDepartmentCourseAndProgram(Integer courseId,
                                                                  Integer departmentId,
                                                                  Integer programId);

}
