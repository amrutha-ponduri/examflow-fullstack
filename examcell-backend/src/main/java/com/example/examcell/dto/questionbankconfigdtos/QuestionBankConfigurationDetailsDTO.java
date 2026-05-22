package com.example.examcell.dto.questionbankconfigdtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class QuestionBankConfigurationDetailsDTO {
    @JsonProperty("modules_info")
    private List<ModuleInfoDTO> modulesInfo;
    @JsonProperty("sections_rules")
    private List<SectionRulesDTO> sectionsRules;
}
