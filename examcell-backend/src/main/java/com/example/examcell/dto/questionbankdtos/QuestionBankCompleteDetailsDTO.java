package com.example.examcell.dto.questionbankdtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionBankCompleteDetailsDTO {
    private Integer id;
    @JsonProperty("review_status")
    private String reviewStatus;
    @JsonProperty("submitted_at")
    private String submittedAt;
    @JsonProperty("course_code")
    private String courseCode;
    @JsonProperty("course_title")
    private String courseTitle;
    @JsonProperty("submitted_by")
    private String submittedBy;
    @JsonProperty("reviewer")
    private String reviewer;
    @JsonProperty("department")
    private String department;
    @JsonProperty("regulation")
    private String regulation;
    @JsonProperty("program")
    private String program;
}
