package com.example.examcell.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sectionrules")
public class SectionRules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "sectionname")
    @JsonProperty("section_name")
    private String sectionName;
    private Integer marks;
    @Column(name = "minquestionscount")
    @JsonProperty("min_questions_count")
    private Integer minQuestionsCount;

    // Foreign key
    @ManyToOne
    @JoinColumn(name = "regulation_id")
    @JsonIgnoreProperties("sectionsRules")
    private Regulation regulation;
}
