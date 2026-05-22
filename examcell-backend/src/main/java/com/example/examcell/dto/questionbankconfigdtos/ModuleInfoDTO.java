package com.example.examcell.dto.questionbankconfigdtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ModuleInfoDTO {
    @JsonProperty("module_no")
    private Integer moduleNo;
    @JsonProperty("module_name")
    private String moduleName;
}
