package com.example.examcell.dto.departmentdtos;

import com.example.examcell.model.Department;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentReviewerDTO {
    private Department department;
    @JsonProperty("user_id")
    private Integer userId;
}
