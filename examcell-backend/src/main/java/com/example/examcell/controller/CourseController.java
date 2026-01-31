package com.example.examcell.controller;

import com.example.examcell.model.Course;
import com.example.examcell.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

// http://localhost:8080/courses
@RestController
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/dropdown")
    public ArrayList<Course> getAllCoursesDropdowns() {
        return courseService.getAllCourses();
    }

    // method -> GET
    // url -> http://localhost:8080/courses/course_code -> give the course_code
    @GetMapping("/{id}")
    public Course getCourseByCourseCode(@PathVariable("id") int id) {
        return courseService.getCourseByCourseCode(id);
    }

    // method -> POST
    // url -> http://localhost:8080/courses -> must give request body
    @PostMapping("")
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    // method -> PUT
    // url -> http://localhost:8080/courses/course_code -> must give course_code & request body
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable("id") int id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    // method -> DELETE
    // url -> http://localhost:8080/courses/course_code -> must give course_code

    @DeleteMapping("/{id}")
    public void deleteCourseByCourseCode(@PathVariable("id") int id) {
        courseService.deleteCourseByCourseCode(id);
    }

}
