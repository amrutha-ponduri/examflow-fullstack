package com.example.examcell.dto;

import com.example.examcell.model.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentReviewerDTO {
    private Department department;
    private Integer userId;
}
