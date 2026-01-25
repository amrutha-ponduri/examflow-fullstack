package com.example.examcell.service;

import com.example.examcell.model.Course;
import com.example.examcell.repository.CourseJpaRepository;
import com.example.examcell.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
public class CourseService implements CourseRepository {

    private final CourseJpaRepository courseJpaRepository;

    @Autowired
    public CourseService(CourseJpaRepository courseJpaRepository) {
        this.courseJpaRepository = courseJpaRepository;
    }

    @Override
    public ArrayList<Course> getAllCourses() {
        return new ArrayList<>(courseJpaRepository.findAll());
    }

    @Override
    public Course getCourseByCourseCode(String courseCode) {
        try {
            return courseJpaRepository.findById(courseCode).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException ie) {
            throw new IllegalArgumentException("Invalid argument");
        }
    }

    @Override
    public Course addCourse(Course course) {
        return courseJpaRepository.save(course);
    }

    @Override
    public Course updateCourse(String courseCode, Course course) {
        try {
            Course savedCourse = getCourseByCourseCode(courseCode);
            if (course.getCourseTitle() != null) {
                savedCourse.setCourseTitle(course.getCourseTitle());
            }
            if (course.getCredits() != null) {
                savedCourse.setCredits(course.getCredits());
            }
            return courseJpaRepository.save(savedCourse);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid argument!");
        }
    }

    @Override
    public void deleteCourseByCourseCode(String courseCode) {
        try {
            Course savedCourse = getCourseByCourseCode(courseCode);
            courseJpaRepository.deleteById(courseCode);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
}
