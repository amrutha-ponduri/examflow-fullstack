package com.example.examcell.dto.departmentdtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepartmentDTO {
    private int id;
    @JsonProperty("department_name")
    private String departmentName;
    @JsonProperty("reviewer_name")
    private String reviewerName;
}
