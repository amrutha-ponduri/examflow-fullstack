package com.example.examcell.dto.questionbankconfigdtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SectionRulesDTO {
    @JsonProperty("min_questions_count")
    private Integer minQuestionsCount;
    @JsonProperty("section_name")
    private String sectionName;
    @JsonProperty("marks")
    private Integer marks;
}
