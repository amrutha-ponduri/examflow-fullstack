package com.example.examcell.dto.dropdowndtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProgramDropdownItemDTO {
    int id;
    @JsonProperty("program_name")
    String programName;
}
