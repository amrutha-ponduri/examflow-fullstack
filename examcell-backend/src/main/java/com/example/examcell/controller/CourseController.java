package com.example.examcell.controller;

import com.example.examcell.model.Course;
import com.example.examcell.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
// http://localhost:8080/courses
@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // method -> GET
    // url -> http://localhost:8080/courses
    @GetMapping("")
    public ArrayList<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // method -> GET
    // url -> http://localhost:8080/courses/course_code -> give the course_code
    @GetMapping("/{courseCode}")
    public Course getCourseByCourseCode(@PathVariable("courseCode") String courseCode) {
        return courseService.getCourseByCourseCode(courseCode);
    }

    // method -> POST
    // url -> http://localhost:8080/courses -> must give request body
    @PostMapping("")
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    // method -> PUT
    // url -> http://localhost:8080/courses/course_code -> must give course_code & request body
    @PutMapping("/{courseCode}")
    public Course updateCourse(@PathVariable("courseCode") String courseCode, @RequestBody Course course) {
        return courseService.updateCourse(courseCode, course);
    }

    // method -> DELETE
    // url -> http://localhost:8080/courses/course_code -> must give course_code

    @DeleteMapping("/{courseCode}")
    public void deleteCourseByCourseCode(@PathVariable("courseCode") String courseCode) {
        courseService.deleteCourseByCourseCode(courseCode);
    }

}
