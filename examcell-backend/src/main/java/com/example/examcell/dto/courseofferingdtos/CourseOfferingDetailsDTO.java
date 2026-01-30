package com.example.examcell.dto.courseofferingdtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseOfferingDetailsDTO {
    private String courseTitle;
    private String yearOfStudy;
    private String semester;
    private String departmentShortName;
    private String academicYear;
}

