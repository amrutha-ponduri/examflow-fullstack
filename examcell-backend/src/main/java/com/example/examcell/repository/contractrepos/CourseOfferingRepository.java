package com.example.examcell.repository.contractrepos;

import com.example.examcell.dto.courseofferingdtos.CourseOfferingCompleteDetailsDTO;
import com.example.examcell.dto.courseofferingdtos.CourseOfferingDTO;
import com.example.examcell.model.CourseOffering;

import java.util.ArrayList;

public interface CourseOfferingRepository {
    public ArrayList<CourseOfferingDTO> getAllCourseOfferings();

    public CourseOfferingCompleteDetailsDTO getCourseOfferingById(int id);

    public CourseOfferingDTO addCourseOffering(CourseOffering courseOffering);

    public CourseOfferingDTO updateCourseOffering(int id, CourseOffering courseOffering);

    public void deleteCourseOffering(int id);

}
