package com.example.examcell.dto.courseofferingdtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CourseOfferingDTO {
    private int id;
    private String semester;
    @JsonProperty("year_of_study")
    private String yearOfStudy;
    @JsonProperty("department_shortname")
    private String departmentShortname;
    private String regulation;
    @JsonProperty("course_title")
    private String courseTitle;
}
