package com.example.examcell.dto.questionbankconfigdtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ConfigurationRequestDTO {
    @JsonProperty("department_id")
    private Integer departmentId;
    @JsonProperty("program_id")
    private Integer programId;
    @JsonProperty("course_id")
    private Integer courseId;
    @JsonProperty("regulation_id")
    private Integer regulationId;
}
