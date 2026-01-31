package com.example.examcell.dto.moduledtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ModuleDTO {
    @JsonProperty("module_no")
    private int moduleNo;
    @JsonProperty("module_name")
    private String moduleName;
}
