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
public class SubquestionDTO {
    private Integer id;
    private String content;
    @JsonProperty("image_urls")
    private List<String> imageURLs;
    private Integer marks;
    @JsonProperty("blooms_level")
    private Integer bloomsLevel;
}
