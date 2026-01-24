package com.example.examcell.repository;

import com.example.examcell.model.Course;

import java.util.ArrayList;

public interface CourseRepository {
    public ArrayList<Course> getAllCourses();
    public Course getCourseByCourseCode(String courseCode);
    public Course addCourse(Course course);
    public Course updateCourse(String courseCode, Course course);
    public void deleteCourseByCourseCode(String courseCode);
}
