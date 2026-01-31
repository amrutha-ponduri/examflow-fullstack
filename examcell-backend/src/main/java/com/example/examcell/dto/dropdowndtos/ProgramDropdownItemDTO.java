package com.example.examcell.dto.dropdowndtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProgramDropdownItemDTO {
    private int id;
    @JsonProperty("program_name")
    private String programName;
}
