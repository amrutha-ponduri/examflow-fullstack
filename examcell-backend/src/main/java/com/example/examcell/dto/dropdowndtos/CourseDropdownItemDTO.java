package com.example.examcell.dto.dropdowndtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseDropdownItemDTO {
    private int id;

    @JsonProperty("course_code")
    private String courseCode;
    @JsonProperty("course_title")
    private String courseTitle;
}
