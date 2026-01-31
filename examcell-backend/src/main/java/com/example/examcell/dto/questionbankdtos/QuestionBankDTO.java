package com.example.examcell.dto.questionbankdtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuestionBankDTO {
    private int id;
    @JsonProperty("review_status")
    private String reviewStatus;
    @JsonProperty("submitted_at")
    private String submittedAt;
    @JsonProperty("course_code")
    private String courseCode;
    @JsonProperty("course_title")
    private String courseTitle;
}
