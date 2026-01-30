package com.example.examcell.dto.courseofferingdtos;

import com.example.examcell.dto.moduledtos.ModuleDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseOfferingCompleteDetailsDTO {
    private int id;
    @JsonProperty("academic_year")
    private String academicYear;
    @JsonProperty("semester")
    private String semester;
    @JsonProperty("year_of_study")
    private String yearOfStudy;
    @JsonProperty("department_name")
    private String departmentName;
    @JsonProperty("course_code")
    private String courseCode;
    @JsonProperty("course_title")
    private String courseTitle;
    @JsonProperty("program_name")
    private String programName;
    @JsonProperty("regulation_name")
    private String regulationName;
    @JsonProperty("submitter_name")
    private String submitterName;
    @JsonProperty("instructor_names")
    private List<String> instructorNames;
    @JsonProperty("modules_info")
    private List<ModuleDTO> modulesInfo;
}
