package com.example.examcell.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepartmentDTO {
    private int id;
    private String departmentName;
    private String reviewerName;
}
