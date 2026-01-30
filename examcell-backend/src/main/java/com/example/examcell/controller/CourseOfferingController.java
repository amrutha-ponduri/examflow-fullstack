package com.example.examcell.controller;

import com.example.examcell.dto.courseofferingdtos.CourseOfferingCompleteDetailsDTO;
import com.example.examcell.dto.courseofferingdtos.CourseOfferingDTO;
import com.example.examcell.model.CourseOffering;
import com.example.examcell.service.CourseOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/courseofferings")
public class CourseOfferingController {
    private final CourseOfferingService courseOfferingService;

    @Autowired
    public CourseOfferingController(CourseOfferingService courseOfferingService) {
        this.courseOfferingService = courseOfferingService;
    }

    @GetMapping("")
    public ArrayList<CourseOfferingDTO> getAllCourseOfferings() {
        return courseOfferingService.getAllCourseOfferings();
    }

    @GetMapping("/{id}")
    public CourseOfferingCompleteDetailsDTO getCourseOfferingById(@PathVariable("id") int id) {
        return courseOfferingService.getCourseOfferingById(id);
    }

    @PostMapping("")
    public CourseOfferingDTO addCourseOffering(@RequestBody CourseOffering courseOffering) {
        return courseOfferingService.addCourseOffering(courseOffering);
    }

    @PutMapping("/{id}")
    public CourseOfferingDTO updateCourseOffering(@PathVariable("id") int id, @RequestBody CourseOffering courseOffering) {
        return courseOfferingService.updateCourseOffering(id, courseOffering);
    }

    @DeleteMapping("/{id}")
    public void deleteCourseOffering(@PathVariable("id") int id) {
        courseOfferingService.deleteCourseOffering(id);
    }


}
