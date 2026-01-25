package com.example.examcell.repository;

import com.example.examcell.model.Course;

import java.util.ArrayList;

public interface CourseRepository {
    public ArrayList<Course> getAllCourses();

    public Course getCourseByCourseCode(int id);

    public Course addCourse(Course course);

    public Course updateCourse(int id, Course course);

    public void deleteCourseByCourseCode(int id);
}
