package com.example.examcell.dto.questiondtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private Integer id;
    private Integer marks;
    private Integer sno;
    @JsonProperty("module_no")
    private Integer moduleNo;
    private List<SubquestionDTO> subquestions;
}
